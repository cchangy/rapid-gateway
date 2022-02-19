package com.chaytech.rapid.core.config.parse;

import java.util.Map;
import java.util.Properties;

/**
 * 配置文件解析接口
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/06
 */
public interface ConfigParse {

    /**
     * 解析配置
     * @return
     */
    Properties parse();
}
