package com.client.client.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer productStatus;
    private String name;

    ProductStatusEnum(Integer productStatus, String name) {
        this.productStatus = productStatus;
        this.name = name;
    }
}
