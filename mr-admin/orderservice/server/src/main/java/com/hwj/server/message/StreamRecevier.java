package com.hwj.server.message;

import com.hwj.server.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(StreamClient.class)
public class StreamRecevier {

    @StreamListener(value = StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String process(OrderDto message){
        log.info("StreamRecevier : {}", message);
        return "recevier";
    }

    @StreamListener(value = StreamClient.INPUT2)
    public void process2(String message){
        log.info("StreamRecevier2 : {}", message);
    }
}
