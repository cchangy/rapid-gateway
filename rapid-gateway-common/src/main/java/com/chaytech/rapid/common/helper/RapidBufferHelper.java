package com.chaytech.rapid.common.helper;

/**
 * 网关缓冲辅助类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2021/12/27
 */
public class RapidBufferHelper {

    public static final String FLUSHER = "FLUSHER";
    public static final String MPMC = "MPMC";

    private RapidBufferHelper() {

    }


    public static boolean isMpmc(String bufferType) {
        return MPMC.equals(bufferType);
    }

    public static boolean isFlusher(String bufferType) {
        return FLUSHER.equals(bufferType);
    }
}
