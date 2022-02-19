package com.chaytech.rapid.core.netty;

import com.chaytech.rapid.common.helper.AsyncHttpHelper;
import com.chaytech.rapid.common.suport.LifeCycle;
import com.chaytech.rapid.core.config.RapidConfig;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;

import java.io.IOException;
import java.util.Objects;

/**
 * Http客户端，主要用于下游服务的请求转发
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/07
 */
@Slf4j
public class NettyHttpClient implements LifeCycle {

    private RapidConfig rapidConfig;
    private EventLoopGroup workEventLoopGroup;

    private AsyncHttpClient asyncHttpClient;
    private DefaultAsyncHttpClientConfig.Builder asyncHttpClientConfigBuilder;

    public NettyHttpClient(RapidConfig rapidConfig, EventLoopGroup workEventLoopGroup) {
        this.rapidConfig = rapidConfig;
        this.workEventLoopGroup = workEventLoopGroup;

        init();
    }

    @Override
    public void init() {
        this.asyncHttpClientConfigBuilder = new DefaultAsyncHttpClientConfig.Builder()
                .setFollowRedirect(false)
                .setEventLoopGroup(workEventLoopGroup)
                .setConnectTimeout(rapidConfig.getHttpConnectTimeout())
                .setRequestTimeout(rapidConfig.getHttpRequestTimeout())
                .setMaxRequestRetry(rapidConfig.getHttpMaxRequestRetry())
                .setAllocator(PooledByteBufAllocator.DEFAULT)
                .setCompressionEnforced(true)
                .setMaxConnections(rapidConfig.getHttpMaxConnections())
                .setMaxConnectionsPerHost(rapidConfig.getHttpConnectionsPerHost())
                .setPooledConnectionIdleTimeout(rapidConfig.getHttpPooledConnectionIdleTimeout());
    }

    @Override
    public void start() {
        this.asyncHttpClient = new DefaultAsyncHttpClient(asyncHttpClientConfigBuilder.build());
        AsyncHttpHelper.getInstance().initialized(asyncHttpClient);
    }

    @Override
    public void shutdown() {
        if (Objects.nonNull(asyncHttpClient)) {
            try {
                asyncHttpClient.close();
            } catch (IOException e) {
                log.error("asyncHttpClient close error", e);
            }
        }
    }
}
