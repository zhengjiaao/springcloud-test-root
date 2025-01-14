/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-13 14:43
 * @Since:
 */
package com.zja.rabbit;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * @author: zhengja
 * @since: 2023/07/13 14:43
 */
@Service
public class RabbitConsumerA {

    /**
     * 登录成功事件
     */
    @Bean
    public Function<Flux<Message<String>>, Mono<Void>> loginConsumerEvent() {
        return flux -> flux.map(message -> {
            System.out.println("登录成功：" + message.getPayload());
            return message;
        }).then();
    }

}
