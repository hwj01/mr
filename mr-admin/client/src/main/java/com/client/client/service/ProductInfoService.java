package com.client.client.service;

import com.client.client.dto.ProductDto;
import com.client.client.entity.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    List<ProductInfo> findUpAll();

    List<ProductInfo> findByProductid(List<String> pIds);

    void changeProductStock(List<ProductDto> List);
}
