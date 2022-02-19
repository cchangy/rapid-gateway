package com.chaytech.rapid.common.utils;

import java.util.concurrent.TimeUnit;

/**
 * 时间工具类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/05
 */
public final class TimeUtil {

    private TimeUtil() {
    }

    private static volatile long currentTimeMillis;

    static {
        currentTimeMillis = System.currentTimeMillis();
        Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    currentTimeMillis = System.currentTimeMillis();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (Throwable e) {

                    }
                }
            }
        });
        daemon.setDaemon(true);
        daemon.setName("common-fd-time-tick-thread");
        daemon.start();
    }

    public static long currentTimeMillis() {
        return currentTimeMillis;
    }
}
