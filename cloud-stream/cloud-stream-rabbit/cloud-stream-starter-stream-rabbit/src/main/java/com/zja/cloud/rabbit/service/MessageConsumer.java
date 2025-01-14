package com.zja.cloud.rabbit.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @Author: zhengja
 * @Date: 2025-01-14 14:04
 */
@Service
@EnableBinding(Sink.class)
public class MessageConsumer {

    @StreamListener(Sink.INPUT)
    public void handleMessage(String message) {
        System.out.println("Received message: " + message);
    }
}