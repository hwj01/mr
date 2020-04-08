package com.hwj.server.converse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwj.server.dto.OrderDto;
import com.hwj.server.entity.OrderDetail;
import com.hwj.server.enums.ExceptionEnum;
import com.hwj.server.exception.OrderException;
import com.hwj.server.formVo.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFormToOrderDto {
    public static OrderDto convert(OrderForm orderForm){
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetails = new ArrayList<>();

        Gson gson = new Gson();
        try{
            orderDetails = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
            orderDto.setOrderDetails(orderDetails);
        }catch (Exception e){
            log.error("【json转换】错误 ，String={}",orderForm.getItems());
            throw new OrderException(ExceptionEnum.PARAM_ERROR);
        }
        return orderDto;
    }
}
