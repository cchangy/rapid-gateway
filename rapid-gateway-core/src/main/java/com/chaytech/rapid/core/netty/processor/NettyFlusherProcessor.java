package com.chaytech.rapid.core.netty.processor;

import com.chaytech.rapid.core.config.RapidConfig;
import com.chaytech.rapid.core.wrapper.HttpRequestWrapper;

/**
 * flusher模式缓冲队列的核心实现, 最终调用的方法还是要回归到NettyCoreProcessor
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/02/19
 */
public class NettyFlusherProcessor implements NettyProcessor {

    private RapidConfig rapidConfig;
    private NettyCoreProcessor nettyCoreProcessor;

    public NettyFlusherProcessor (RapidConfig rapidConfig, NettyCoreProcessor nettyCoreProcessor) {
        this.rapidConfig = rapidConfig;
        this.nettyCoreProcessor = nettyCoreProcessor;
    }

    @Override
    public void process(HttpRequestWrapper httpRequestWrapper) {

    }

    @Override
    public void start() {

    }

    @Override
    public void shutdown() {

    }
}
