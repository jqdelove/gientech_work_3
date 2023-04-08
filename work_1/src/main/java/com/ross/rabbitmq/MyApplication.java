package com.ross.rabbitmq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @description: 访问rabbitmq
 * @author: jinqi
 * @create: 2023-04-08 21:44
 **/
@EnableBinding(Sink.class)
public class MyApplication {

    @StreamListener(Sink.INPUT)
    public void handle(String message) {
        System.out.println("Received message: " + message);
    }
}

