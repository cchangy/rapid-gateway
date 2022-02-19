package com.chaytech.rapid.core.netty;

import com.chaytech.rapid.common.suport.LifeCycle;
import com.chaytech.rapid.common.utils.RemotingUtil;
import com.chaytech.rapid.core.config.RapidConfig;
import com.chaytech.rapid.core.netty.handler.NettyHttpServerHandler;
import com.chaytech.rapid.core.netty.handler.NettyServerConnectManagerHandler;
import com.chaytech.rapid.core.netty.processor.NettyProcessor;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * Http服务端，承接所有网络请求的核心类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/07
 */
@Slf4j
public class NettyHttpServer implements LifeCycle {

    private int port = 8888;
    private RapidConfig rapidConfig;
    private NettyProcessor nettyProcessor;

    private ServerBootstrap serverBootstrap;
    private EventLoopGroup bossEventLoopGroup;
    private EventLoopGroup workEventLoopGroup;

    public NettyHttpServer(RapidConfig rapidConfig, NettyProcessor nettyProcessor) {
        this.rapidConfig = rapidConfig;
        this.nettyProcessor = nettyProcessor;
        // 端口有效性校验
        if (rapidConfig.getPort() > 0 && rapidConfig.getPort() < 65535) {
            this.port = rapidConfig.getPort();
        }

        // 初始化
        init();
    }

    /**
     * 获取netty服务端的workGroup
     *
     * @return
     */
    public EventLoopGroup getWorkEventLoopGroup() {
        return workEventLoopGroup;
    }

    @Override
    public void init() {
        this.serverBootstrap = new ServerBootstrap();
        if (isUseEPoll()) {
            bossEventLoopGroup = new EpollEventLoopGroup(rapidConfig.getEventLoopGroupBossNum(),
                    new DefaultThreadFactory("EPollBossGroup"));
            workEventLoopGroup = new EpollEventLoopGroup(rapidConfig.getEventLoopGroupWorkNum(),
                    new DefaultThreadFactory("EPollWorkGroup"));
        } else {
            bossEventLoopGroup = new NioEventLoopGroup(rapidConfig.getEventLoopGroupBossNum(),
                    new DefaultThreadFactory("NioBossGroup"));
            workEventLoopGroup = new NioEventLoopGroup(rapidConfig.getEventLoopGroupWorkNum(),
                    new DefaultThreadFactory("NioWorkGroup"));
        }
    }

    /**
     * 判断是否支持EPoll
     *
     * @return
     */
    private boolean isUseEPoll() {
        return rapidConfig.isUseEPoll() && RemotingUtil.isLinuxPlatform() && Epoll.isAvailable();
    }

    @Override
    public void start() {
        this.serverBootstrap.group(bossEventLoopGroup, workEventLoopGroup)
                .channel(isUseEPoll() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)         // sync + accept = backlog
                .option(ChannelOption.SO_REUSEADDR, true)       // tcp端口重绑定
                .option(ChannelOption.SO_KEEPALIVE, false)      // 如果在两小时内没有数据通信的时候，TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.TCP_NODELAY, true)   // 该参数的左右就是禁用Nagle算法，使用小数据传输时合并
                .childOption(ChannelOption.SO_SNDBUF, 65535)    // 设置发送数据缓冲区大小
                .childOption(ChannelOption.SO_RCVBUF, 65535)    // 设置接收数据缓冲区大小
                .localAddress(port)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(rapidConfig.getMaxContentLength()))
                                .addLast(new HttpServerExpectContinueHandler())
                                .addLast(new NettyServerConnectManagerHandler())
                                .addLast(new NettyHttpServerHandler(nettyProcessor));
                    }
                });

        // 是否开启netty内存分配机制
        if (rapidConfig.isNettyAllocator()) {
            this.serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        }

        try {
            this.serverBootstrap.bind().sync();
            log.info("< ============= Rapid Server StartUp On Port: " + this.port + "================ >");
        } catch (Exception e) {
            throw new RuntimeException("this.serverBootstrap.bind().sync() fail!", e);
        }
    }

    @Override
    public void shutdown() {
        if (Objects.nonNull(bossEventLoopGroup)) {
            bossEventLoopGroup.shutdownGracefully();
        }
        if (Objects.nonNull(workEventLoopGroup)) {
            workEventLoopGroup.shutdownGracefully();
        }
    }
}
