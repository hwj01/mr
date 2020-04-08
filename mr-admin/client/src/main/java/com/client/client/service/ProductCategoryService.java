package com.client.client.service;

import com.client.client.entity.ProductCategory;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findTypeAll(List<Integer> types);
}
