package com.hwj.server.service.impl;

import com.hwj.product.client.ProductClient;
import com.hwj.product.common.ProductInfoInput;
import com.hwj.product.common.ProductInfoOutput;
import com.hwj.server.dto.OrderDto;
import com.hwj.server.entity.OrderDetail;
import com.hwj.server.entity.OrderMaster;
import com.hwj.server.enums.OrderStatusEnum;
import com.hwj.server.enums.PayOrderEnum;
import com.hwj.server.repository.OrderDetailRepository;
import com.hwj.server.repository.OrderMasterRepository;
import com.hwj.server.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMasterRepository orderMasterRepository;

    @Resource
    private OrderDetailRepository orderDetailRepository;

    @Resource
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        String orderId = "hgrhgijk" + System.currentTimeMillis();
        //查询商品信息
        List<OrderDetail> orderDetails = orderDto.getOrderDetails();
        List<String> strings = orderDetails.stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> products = productClient.getProductInfo(strings);

        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (ProductInfoOutput productInfo:products) {
            for (OrderDetail orderDetail:orderDetails) {
                if(productInfo.getProductId().equals(orderDetail.getProductId())){
                    orderAmout = productInfo.getProductPrice().multiply(
                            new BigDecimal(orderDetail.getProductQuantity())).add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setDetailId("detail"+System.currentTimeMillis());
                    orderDetail.setOrderId(orderId);
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //口库存
        List<ProductInfoInput> orderDtos = orderDetails.stream().map(e -> new ProductInfoInput(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.changeStock(orderDtos);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayOrderEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmout);

        orderMasterRepository.save(orderMaster);
        return orderDto;
    }
}
