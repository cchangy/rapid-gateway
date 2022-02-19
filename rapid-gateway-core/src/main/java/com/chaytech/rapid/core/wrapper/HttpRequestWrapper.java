package com.chaytech.rapid.core.wrapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * http请求包装类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/02/19
 */
@Data
@AllArgsConstructor
public class HttpRequestWrapper {

    private FullHttpRequest fullHttpRequest;
    private ChannelHandlerContext channelHandlerContext;
}
