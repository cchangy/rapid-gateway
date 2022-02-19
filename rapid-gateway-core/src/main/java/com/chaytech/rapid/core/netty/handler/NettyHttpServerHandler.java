package com.chaytech.rapid.core.netty.handler;

import com.chaytech.rapid.core.netty.processor.NettyProcessor;
import com.chaytech.rapid.core.wrapper.HttpRequestWrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Netty核心处理handler
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/07
 */
@Slf4j
public class NettyHttpServerHandler extends ChannelInboundHandlerAdapter {

    private NettyProcessor nettyProcessor;

    public NettyHttpServerHandler(NettyProcessor nettyProcessor) {
        this.nettyProcessor = nettyProcessor;
    }

    /**
     * 处理请求
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(fullHttpRequest, ctx);

            nettyProcessor.process(httpRequestWrapper);
        } else {
            //	never go this way, ignore
            log.error("message type is not httpRequest: {}", msg);
            boolean release = ReferenceCountUtil.release(msg);
            if (!release) {
                log.error("release fail");
            }
        }
    }
}
