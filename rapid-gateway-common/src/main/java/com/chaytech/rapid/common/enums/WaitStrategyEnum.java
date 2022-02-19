package com.chaytech.rapid.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 网关等待策略
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/06
 */
@Getter
@AllArgsConstructor
public enum WaitStrategyEnum {

    BLOCKING("blocking", "阻塞");

    private String value;
    private String desc;
}
