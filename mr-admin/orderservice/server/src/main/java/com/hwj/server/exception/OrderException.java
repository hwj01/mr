package com.hwj.server.exception;


import com.hwj.server.enums.ExceptionEnum;

public class OrderException extends RuntimeException{
    private Integer code;
    public OrderException(Integer code, String message){
        super(message);
        this.code=code;
    }
    public OrderException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }
}
