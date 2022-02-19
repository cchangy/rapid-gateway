package com.chaytech.rapid.core;

import com.chaytech.rapid.core.config.RapidConfig;
import com.chaytech.rapid.core.config.RapidConfigLoader;
import com.chaytech.rapid.core.container.RapidContainer;
import lombok.extern.slf4j.Slf4j;

/**
 * 网关主启动类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/05
 */
@Slf4j
public class RapidBootstrap {

    public static void main(String[] args) {
        // 1. 加载网关配置
        RapidConfig rapidConfig = RapidConfigLoader.getInstance().load(args);

        // 2. 插件初始化

        // 3. 初始化服务注册管理器, 监听动态配置的新增、修改、删除

        // 4. 启动容器
        RapidContainer rapidContainer = new RapidContainer(rapidConfig);
        rapidContainer.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                rapidContainer.shutdown();
            }
        }));
    }
}
