package com.hwj.server.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    PARAM_ERROR(1, "参数错误");

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
