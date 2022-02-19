package com.chaytech.rapid.core.config.parse;

import java.util.Properties;

/**
 * 配置文件解析构建器
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/06
 */
public class ConfigParseBuilder {

    public static Properties parse(ConfigParse configParse) {
        return configParse.parse();
    }
}
