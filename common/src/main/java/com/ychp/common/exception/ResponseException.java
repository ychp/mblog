package com.ychp.common.exception;

import lombok.Getter;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
public class ResponseException extends RuntimeException {

    private static final long serialVersionUID = -8674253694601200912L;

    @Getter
    private final String errorCode;
    @Getter
    private final Integer status;

    public ResponseException(String errorCode, String message) {
        this(errorCode, 500, message);
    }

    public ResponseException(String errorCode, Integer status, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public ResponseException(String errorCode, String message, Throwable cause) {
        this(errorCode, 500, message, cause);
    }

    public ResponseException(String errorCode, Integer status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.errorCode = errorCode;
    }
}
