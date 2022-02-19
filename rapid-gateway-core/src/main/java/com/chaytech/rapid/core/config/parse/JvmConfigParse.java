package com.chaytech.rapid.core.config.parse;

import com.chaytech.rapid.common.constants.BasicConst;
import com.google.common.base.CaseFormat;

import java.util.Map;
import java.util.Properties;

/**
 * jvm配置解析
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/06
 */
public class JvmConfigParse implements ConfigParse {

    // 配置前缀
    private final static String RAPID_CONFIG_PREFIX = "rapid.";

    @Override
    public Properties parse() {
        Properties properties = new Properties();
        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            String key = (String) entry.getKey();
            if (!key.startsWith(RAPID_CONFIG_PREFIX)) {
                continue;
            }

            // jvm参数的名称转换为属性名
            String configKey = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
                    key.replace(RAPID_CONFIG_PREFIX, BasicConst.BLANK_SEPARATOR_1)
                            .replace(BasicConst.DIT_SEPARATOR, BasicConst.UNDERSCORE_SEPARATOR));
            properties.put(configKey, entry.getValue());
        }
        return properties;
    }
}
