package com.hwj.server;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class ServerApplicationTests {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Test
    void contextLoads() {
        amqpTemplate.convertAndSend("myOrder","computer", new Date());
    }

}
