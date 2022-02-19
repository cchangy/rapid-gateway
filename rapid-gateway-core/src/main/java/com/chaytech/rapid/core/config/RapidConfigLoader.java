package com.chaytech.rapid.core.config;

import com.chaytech.rapid.common.utils.PropertiesUtil;
import com.chaytech.rapid.core.config.parse.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * 网关配置加载类
 * <p>
 * 网关配置加载规则：优先级高的会覆盖掉优先级低的配置数据
 * 优先级：运行参数(最高) -> jvm参数 -> 环境变量 -> 配置文件 -> RapidConfig属性默认值(最低);
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/05
 */
@Slf4j
public class RapidConfigLoader {

    private final static RapidConfigLoader INSTANCE = new RapidConfigLoader();

    private static RapidConfig RAPID_CONFIG_INSTANCE = new RapidConfig();

    private RapidConfigLoader() {
    }

    public static RapidConfigLoader getInstance() {
        return INSTANCE;
    }

    public static RapidConfig getRapidConfig() {
        return INSTANCE.RAPID_CONFIG_INSTANCE;
    }

    /**
     * 加载配置
     *
     * @param args 启动参数
     * @return
     */
    public RapidConfig load(String args[]) {
        Properties rapidConfig = new Properties();
        // 1. 配置文件
        rapidConfig.putAll(ConfigParseBuilder.parse(new PropertiesConfigParse()));

        // 2. 环境变量
        rapidConfig.putAll(ConfigParseBuilder.parse(new EnvironmentConfigParse()));

        // 3. jvm参数
        rapidConfig.putAll(ConfigParseBuilder.parse(new JvmConfigParse()));

        // 4. 运行参数
        rapidConfig.putAll(ConfigParseBuilder.parse(new RuntimeConfigParse(args)));

        PropertiesUtil.properties2Object(rapidConfig, RAPID_CONFIG_INSTANCE);
        return RAPID_CONFIG_INSTANCE;
    }
}
