/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-25 15:50
 * @Since:
 */
package com.zja.send;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 *
 */
@SpringBootTest
public class MessageChannelTests {


    MessageChannel messageChannel = new DirectChannel();

    /**
     * 消息类 spring-messaging 模块里提供
     */
    @Test
    public void send() {
        // 消息订阅
        ((SubscribableChannel) messageChannel).subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("receive msg: " + message.getPayload());
            }
        });

        // 消息发送
        messageChannel.send(MessageBuilder.withPayload("simple msg").build());
    }

}
