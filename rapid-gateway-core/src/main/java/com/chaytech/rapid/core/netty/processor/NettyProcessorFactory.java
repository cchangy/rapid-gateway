package com.chaytech.rapid.core.netty.processor;

import com.chaytech.rapid.common.helper.RapidBufferHelper;
import com.chaytech.rapid.core.config.RapidConfig;

/**
 * 核心处理器工厂类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/02/19
 */
public class NettyProcessorFactory {
    
    public static NettyProcessor getNettyProcessor(String bufferType, RapidConfig rapidConfig) {
        NettyCoreProcessor nettyCoreProcessor = new NettyCoreProcessor();
        if (RapidBufferHelper.isFlusher(bufferType)) {
            return new NettyFlusherProcessor(rapidConfig, nettyCoreProcessor);
        } else if (RapidBufferHelper.isMpmc(bufferType)) {
            return new NettyMpmcProcessor(rapidConfig, nettyCoreProcessor);
        }
        return nettyCoreProcessor;
    }
}
