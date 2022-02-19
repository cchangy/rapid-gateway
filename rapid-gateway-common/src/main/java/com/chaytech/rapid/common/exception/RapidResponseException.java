package com.chaytech.rapid.common.exception;

import com.chaytech.rapid.common.enums.ResponseCodeEnum;

/**
 * 网关响应异常类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/05
 */
public class RapidResponseException extends RapidBaseException {

    private static final long serialVersionUID = 7364316766851078107L;

    public RapidResponseException() {
        this(ResponseCodeEnum.INTERNAL_ERROR);
    }

    public RapidResponseException(ResponseCodeEnum code) {
        super(code.getMessage(), code);
    }

    public RapidResponseException(Throwable cause, ResponseCodeEnum code) {
        super(code.getMessage(), cause, code);
        this.code = code;
    }
}
