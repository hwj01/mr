package com.client.client.service.impl;

import com.client.client.dao.ProductCategoryRepository;
import com.client.client.entity.ProductCategory;
import com.client.client.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findTypeAll(List<Integer> types) {
        return productCategoryRepository.findByCategoryTypeIn(types);
    }
}
