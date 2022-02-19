package com.chaytech.rapid.core.config.parse;

import com.chaytech.rapid.common.constants.BasicConst;
import com.google.common.base.CaseFormat;

import java.util.Map;
import java.util.Properties;

/**
 * 环境变量配置解析
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/06
 */
public class EnvironmentConfigParse implements ConfigParse {

    // 环境变量配置前缀
    private final static String RAPID_CONFIG_ENV_PREFIX = "RAPID_";

    @Override
    public Properties parse() {
        Properties properties = new Properties();
        Map<String, String> systemEnv = System.getenv();
        for (Map.Entry<String, String> entry : systemEnv.entrySet()) {
            String key = entry.getKey();
            if (!key.startsWith(RAPID_CONFIG_ENV_PREFIX)) {
                continue;
            }

            // 环境变量的名称转换为属性名
            String configKey = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
                    key.replace(RAPID_CONFIG_ENV_PREFIX, BasicConst.BLANK_SEPARATOR_1));
            properties.put(configKey, entry.getValue());
        }
        return properties;
    }
}
