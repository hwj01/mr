package com.hwj.server.message;

import cn.hutool.json.JSONUtil;
import com.hwj.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductRecevier {
    @RabbitListener(queuesToDeclare = @Queue("productQueue"))
    public void process(String message){
        ProductInfoOutput productInfoOutput = JSONUtil.toBean(message, ProductInfoOutput.class);
        log.info("prodictInfo{}", productInfoOutput);
    }
}
