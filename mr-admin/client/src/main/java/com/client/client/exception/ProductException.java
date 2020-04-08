package com.client.client.exception;

import com.client.client.enums.ExceptionEnum;

public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException (Integer code, String message){
        super(message);
        this.code = code;
    }

    public ProductException (ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }
}
