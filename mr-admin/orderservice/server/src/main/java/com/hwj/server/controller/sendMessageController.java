package com.hwj.server.controller;

import com.hwj.server.dto.OrderDto;
import com.hwj.server.message.StreamClient;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class sendMessageController {

    @Resource
    private StreamClient streamClient;

    @GetMapping("/message/send")
    public void send(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId("43523462346");
        streamClient.output().send(MessageBuilder.withPayload(orderDto).build());
    }
}
