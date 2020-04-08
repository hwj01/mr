package com.client.client.service.impl;

import com.client.client.dao.ProductInfoRepository;
import com.client.client.dto.ProductDto;
import com.client.client.entity.ProductInfo;
import com.client.client.enums.ExceptionEnum;
import com.client.client.enums.ProductStatusEnum;
import com.client.client.exception.ProductException;
import com.client.client.result.ResultVo;
import com.client.client.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductInfoRepository productInfoRepository;
    @Override
    public List<ProductInfo> findUpAll() {
        /**
         * 查询所有上架商品
         */
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getProductStatus());
    }

    @Override
    public List<ProductInfo> findByProductid(List<String> pIds) {
        return productInfoRepository.findByProductIdIn(pIds);
    }

    @Override
    @Transactional
    public void changeProductStock(List<ProductDto> productDtos) {
        productDtos.forEach(product -> {
            Optional<ProductInfo> infoOptional = productInfoRepository.findById(product.getProductId());
            if(!infoOptional.isPresent()){
                log.error("【减库存】商品不存在: product{}",infoOptional);
                throw new ProductException(ExceptionEnum.PRODUCT_NOT_EXSIT);
            }
            ProductInfo productInfo = infoOptional.get();
            Integer result = productInfo.getProductStock() - product.getProductStock();
            if(result < 0){
                log.error("【减库存】库存不足: product{}",productInfo.getProductStock());
                throw new ProductException(ExceptionEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        });
    }
}
