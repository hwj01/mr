package com.hwj.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MqRecevier {

    @RabbitListener(queuesToDeclare = @Queue("productQueue"))
    public void process(String message){
        log.info("prodictInfo{}", message);
    }

    /**
     * 数码商品消息接收服务
     * @param message
     */
    @RabbitListener(bindings =
    @QueueBinding(exchange = @Exchange("myOrder"),key = "computer", value = @Queue("computerQueue"))
    )
    public void processComputer(String message){
        log.info(" Computer MyRecevier: {}", message);
    }

    /**
     * 数码商品消息接收服务
     * @param message
     */
    @RabbitListener(bindings =
    @QueueBinding(exchange = @Exchange("myOrder"),key = "furits", value = @Queue("furitsQueue"))
    )
    public void processFurits(String message){
        log.info(" Furits MyRecevier: {}", message);
    }
}
