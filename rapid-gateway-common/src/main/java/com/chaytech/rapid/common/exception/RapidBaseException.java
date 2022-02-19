package com.chaytech.rapid.common.exception;

import com.chaytech.rapid.common.enums.ResponseCodeEnum;

/**
 * 网关基础异常类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/05
 */
public class RapidBaseException extends RuntimeException {

    private static final long serialVersionUID = 8330096124441292089L;

    public RapidBaseException() {
    }

    protected ResponseCodeEnum code;

    public RapidBaseException(String message, ResponseCodeEnum code) {
        super(message);
        this.code = code;
    }

    public RapidBaseException(String message, Throwable cause, ResponseCodeEnum code) {
        super(message, cause);
        this.code = code;
    }

    public RapidBaseException(ResponseCodeEnum code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public RapidBaseException(String message, Throwable cause,
                              boolean enableSuppression, boolean writableStackTrace, ResponseCodeEnum code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public ResponseCodeEnum getCode() {
        return code;
    }
}
