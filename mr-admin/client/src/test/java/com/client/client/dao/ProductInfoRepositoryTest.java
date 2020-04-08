package com.client.client.dao;

import com.client.client.entity.ProductInfo;
import com.client.client.enums.ProductStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoRepositoryTest {

    @Resource
    private ProductInfoRepository productInfoRepository;

    @Test
    void findByProductStatus() {
        List<ProductInfo> infoList = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getProductStatus());
        Assert.isTrue(infoList.size()>0,"success");

        List<ProductInfo> infos = productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160022","157875227953464068"));
        Assert.isTrue(infos.size()>0,"success");
    }
}