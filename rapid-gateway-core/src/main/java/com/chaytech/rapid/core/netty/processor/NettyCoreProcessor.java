package com.chaytech.rapid.core.netty.processor;

import com.chaytech.rapid.core.wrapper.HttpRequestWrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Netty核心处理器
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/02/19
 */
@Slf4j
public class NettyCoreProcessor implements NettyProcessor {

    @Override
    public void process(HttpRequestWrapper httpRequestWrapper) {
        FullHttpRequest request = httpRequestWrapper.getFullHttpRequest();
        ChannelHandlerContext channelHandlerContext = httpRequestWrapper.getChannelHandlerContext();

    }

    @Override
    public void start() {

    }

    @Override
    public void shutdown() {

    }
}
