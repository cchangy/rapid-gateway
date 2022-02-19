package com.chaytech.rapid.common.constants;

import java.util.regex.Pattern;

/**
 * 基础常量类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2021/12/27
 */
public class BasicConst {

    private BasicConst() {

    }

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final String PATH_SEPARATOR = "/";

    public static final String PATH_PATTERN = "/**";

    public static final String QUESTION_SEPARATOR = "?";

    public static final String ASTERISK_SEPARATOR = "*";

    public static final String AND_SEPARATOR = "&";

    public static final String EQUAL_SEPARATOR = "=";

    public static final String BLANK_SEPARATOR_1 = "";

    public static final String BLANK_SEPARATOR_2 = " ";

    public static final String COMMA_SEPARATOR = ",";

    public static final String SEMICOLON_SEPARATOR = ";";

    public static final String DOLLAR_SEPARATOR = "$";

    public static final String PIPELINE_SEPARATOR = "|";

    public static final String BAR_SEPARATOR = "-";

    public static final String UNDERSCORE_SEPARATOR = "_";

    public static final String COLON_SEPARATOR = ":";

    public static final String DIT_SEPARATOR = ".";

    public static final String HTTP_PREFIX_SEPARATOR = "http://";

    public static final String HTTPS_PREFIX_SEPARATOR = "https://";

    public static final String HTTP_FORWARD_SEPARATOR = "X-Forwarded-For";

    public static Pattern PARAM_PATTERN = Pattern.compile("\\{(.*?)\\}");

    public static final String ENABLE = "Y";

    public static final String DISABLE = "N";

    public static final String DOUBLE_BAR_SEPARATOR = "--";
}
