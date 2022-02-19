package com.chaytech.rapid.core.container;

import com.chaytech.rapid.common.suport.LifeCycle;
import com.chaytech.rapid.core.config.RapidConfig;
import com.chaytech.rapid.core.netty.NettyHttpClient;
import com.chaytech.rapid.core.netty.NettyHttpServer;
import com.chaytech.rapid.core.netty.processor.NettyProcessor;
import com.chaytech.rapid.core.netty.processor.NettyProcessorFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 网关容器类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/07
 */
@Slf4j
public class RapidContainer implements LifeCycle {

    private RapidConfig rapidConfig; // 网关配置
    private NettyHttpServer nettyHttpServer; //	接收http请求的server
    private NettyHttpClient nettyHttpClient; // http转发的核心类
    private NettyProcessor nettyProcessor; // 核心处理器

    public RapidContainer(RapidConfig rapidConfig) {
        this.rapidConfig = rapidConfig;
        this.init();
    }

    @Override
    public void init() {
        // 1. 根据缓冲模式创建缓冲处理器
        this.nettyProcessor = NettyProcessorFactory.getNettyProcessor(rapidConfig.getBufferType(), rapidConfig);

        // 2. 创建http服务端
        this.nettyHttpServer = new NettyHttpServer(rapidConfig, nettyProcessor);

        // 3. 创建http客户端
        this.nettyHttpClient = new NettyHttpClient(rapidConfig, this.nettyHttpServer.getWorkEventLoopGroup());
    }

    @Override
    public void start() {
        nettyProcessor.start();
        nettyHttpServer.start();
        nettyHttpClient.start();
        log.info("RapidContainer started");
    }

    @Override
    public void shutdown() {
        nettyProcessor.shutdown();
        nettyHttpServer.shutdown();
        nettyHttpClient.shutdown();
    }
}
