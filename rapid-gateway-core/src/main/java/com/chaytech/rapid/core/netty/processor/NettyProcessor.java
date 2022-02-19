package com.chaytech.rapid.core.netty.processor;

import com.chaytech.rapid.core.wrapper.HttpRequestWrapper;

/**
 * Netty核心处理器
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/07
 */
public interface NettyProcessor {

    /**
     * 处理器处理方法
     *
     * @param httpRequestWrapper
     */
    void process(HttpRequestWrapper httpRequestWrapper);

    /**
     * 启动处理器
     */
    void start();

    /**
     * 关闭处理器
     */
    void shutdown();
}
