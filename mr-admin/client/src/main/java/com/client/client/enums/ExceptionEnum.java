package com.client.client.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    PARAM_ERROR(0,"参数为空"),
    PRODUCT_NOT_EXSIT(0,"商品不存在"),
    PRODUCT_STOCK_ERROR(0,"库存不足"),
    ;
    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
