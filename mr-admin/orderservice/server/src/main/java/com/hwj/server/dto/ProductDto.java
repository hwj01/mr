package com.hwj.server.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String productId;

    private Integer productStock;

    public ProductDto(){

    }

    public ProductDto(String productId, Integer productStock) {
        this.productId = productId;
        this.productStock = productStock;
    }
}
