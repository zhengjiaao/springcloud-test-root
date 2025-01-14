package com.zja.cloud.rabbit.controller;

import com.zja.cloud.rabbit.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhengja
 * @Date: 2025-01-14 14:04
 */
@RestController
@RequestMapping("/message")
public class MessageProducerController {

    @Autowired
    MessageProducer messageProducer;

    public MessageProducerController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @RequestMapping("/send")
    public String sendMessage(String message) {
        messageProducer.sendMessage(message);
        return "success";
    }
}
