package com.zja.cloud.rabbit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @Author: zhengja
 * @Date: 2025-01-14 14:02
 */
@Service
@EnableBinding(Source.class)
public class MessageProducer {

    @Autowired
    private Source source;

    public void sendMessage(String message) {
        source.output().send(MessageBuilder.withPayload(message).build());
    }
}