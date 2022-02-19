package com.chaytech.rapid.core.config.parse;

import com.chaytech.rapid.common.constants.BasicConst;
import com.chaytech.rapid.core.config.RapidConfigLoader;
import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件解析
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/06
 */
@Slf4j
public class PropertiesConfigParse implements ConfigParse {

    // 配置文件名称
    private final static String RAPID_CONFIG_FILE = "rapid.properties";
    // 配置前缀
    private final static String RAPID_CONFIG_PREFIX = "rapid.";

    @Override
    public Properties parse() {
        Properties properties = new Properties();
        try (InputStream inputStream = RapidConfigLoader.class.getClassLoader().getResourceAsStream(RAPID_CONFIG_FILE)) {
            Properties fileProperties = new Properties();
            fileProperties.load(inputStream);

            for (Map.Entry<Object, Object> entry : fileProperties.entrySet()) {
                String key = (String) entry.getKey();
                if (!key.startsWith(RAPID_CONFIG_PREFIX)) {
                    continue;
                }

                // 配置名称转换为属性名
                String configKey = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
                        key.replace(RAPID_CONFIG_PREFIX, BasicConst.BLANK_SEPARATOR_1)
                                .replace(BasicConst.DIT_SEPARATOR, BasicConst.UNDERSCORE_SEPARATOR));
                properties.put(configKey, entry.getValue());
            }
        } catch (IOException e) {
            log.warn("load config file: {} is error", RAPID_CONFIG_FILE, e);
        }
        return properties;
    }
}
