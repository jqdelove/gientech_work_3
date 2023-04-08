package com.ross.kafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @description: 连接kafka
 * @author: jinqi
 * @create: 2023-04-08 21:49
 **/
@EnableBinding({Source.class, Processor.class})
public class MyApplication {

    @StreamListener(Processor.INPUT)
    @SendTo(Source.OUTPUT)
    public String handle(String message) {
        System.out.println("Received message: " + message);
        return message.toUpperCase();
    }
}

