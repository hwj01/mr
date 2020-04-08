package com.hwj.server.controller;

import com.hwj.server.converse.OrderFormToOrderDto;
import com.hwj.server.dto.OrderDto;
import com.hwj.server.enums.ExceptionEnum;
import com.hwj.server.exception.OrderException;
import com.hwj.server.formVo.OrderForm;
import com.hwj.server.result.ResultVo;
import com.hwj.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVo create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数为空,orderForm{}",orderForm);
            throw new OrderException(ExceptionEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderFormToOrderDto.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetails())){
            log.error("【创建订单】购物车为空，items{}",orderDto.getOrderDetails());
        }
        Map<String,String> map = new HashMap();
        OrderDto dto = orderService.create(orderDto);
        map.put("orderId", dto.getOrderId());
        return ResultVo.success(map);
    }
}
