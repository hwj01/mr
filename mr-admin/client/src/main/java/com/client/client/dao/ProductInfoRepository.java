package com.client.client.dao;

import com.client.client.entity.ProductInfo;
import com.client.client.enums.ProductStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus (Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> pIds);
}
