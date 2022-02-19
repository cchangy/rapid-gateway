package com.chaytech.rapid.common.suport;

/**
 * 生命周期管理接口
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/05
 */
public interface LifeCycle {

    /**
     * 组件初始化
     */
    void init();

    /**
     * 组件启动
     */
    void start();

    /**
     * 组件关闭
     */
    void shutdown();
}
