package com.chaytech.rapid.core.config.parse;

import com.chaytech.rapid.common.constants.BasicConst;

import java.util.Properties;

/**
 * 运行参数解析
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/06
 */
public class RuntimeConfigParse implements ConfigParse {

    private String[] args;

    public RuntimeConfigParse(String args[]) {
        this.args = args;
    }

    @Override
    public Properties parse() {
        Properties properties = new Properties();
        if (args != null && args.length > 0) {
            for (String args : args) {
                // 运行参数（--xxx=xxx --enable=true  --port=1234）
                if (args.startsWith(BasicConst.DOUBLE_BAR_SEPARATOR) && args.contains(BasicConst.EQUAL_SEPARATOR)) {
                    String key = args.substring(2, args.indexOf(BasicConst.EQUAL_SEPARATOR));
                    String value = args.substring(args.indexOf(BasicConst.EQUAL_SEPARATOR) + 1);
                    properties.put(key, value);
                }
            }
        }
        return properties;
    }
}
