package com.client.client.controller;

import com.client.client.dto.ProductDto;
import com.client.client.entity.ProductCategory;
import com.client.client.entity.ProductInfo;
import com.client.client.enums.ExceptionEnum;
import com.client.client.exception.ProductException;
import com.client.client.result.ProductCategoryVo;
import com.client.client.result.ProductInfoVo;
import com.client.client.result.ResultVo;
import com.client.client.service.ProductCategoryService;
import com.client.client.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductCtroller {

    @Resource
    private ProductCategoryService productCategoryService;
    @Resource
    private ProductInfoService productInfoService;

    @GetMapping("/productList")
    public ResultVo<ProductCategoryVo> productList(){
        /**所有上架商品*/
        List<ProductInfo> list = productInfoService.findUpAll();
        /**获取商品中存在的categoryType*/
        List<Integer> categroyTypeList = list.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        /**1，2的商品类型*/
        List<ProductCategory> categories = productCategoryService.findTypeAll(categroyTypeList);

        ArrayList<ProductCategoryVo> productList = new ArrayList<>();
        for (ProductCategory category : categories){
            ProductCategoryVo categoryVo = new ProductCategoryVo();
            categoryVo.setCategoryName(category.getCategoryName());
            categoryVo.setCategoryType(category.getCategoryType());
            ArrayList<ProductInfoVo> productInfoVos = new ArrayList<>();
            for (ProductInfo productInfo : list){
                if(category.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            categoryVo.setProductInfoVoList(productInfoVos);
            productList.add(categoryVo);
        }
        return ResultVo.success(productList);
    }

    @PostMapping("/partProduct")
    public List<ProductInfo> partProduct(@RequestBody List<String> pIds){
        if(CollectionUtils.isEmpty(pIds)){
            log.error("【根据指定id查询商品列表】参数为空：pIds={}",pIds);
            throw new ProductException(ExceptionEnum.PARAM_ERROR);
        }
        List<ProductInfo> productInfoList = productInfoService.findByProductid(pIds);
        return productInfoList;
    }

    @PostMapping("/changeStock")
    public void changeStock(@RequestBody List<ProductDto> products){
        if(CollectionUtils.isEmpty(products)){
            log.error("【根据指定id查询商品列表】参数为空：products={}",products);
            throw new ProductException(ExceptionEnum.PARAM_ERROR);
        }
        productInfoService.changeProductStock(products);
    }
}
