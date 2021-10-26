/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-25 16:09
 * @Since:
 */
package com.zja.receive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 *
 */
@Slf4j
@Component
public class ReceiveMessageConsumer {

    private static String consumer = "B";


    //这里接收rabbitmq的条件是参数为Consumer 并且 方法名和supplier方法名相同
    //这里的返回值是一个匿名函数 返回类型是consumer 类型和提供者的类型一致
    //supplier发送的exchange是 send-in-0 这里只需要用send方法名即可

    /**
     * 函数式编程的方式，其中方法名，要与通道名的名称一致
     * @return
     */
    @Bean
    Consumer<String> send() {
        return str -> {
            System.out.println("我是消费者" + consumer + "，我收到了消息：" + str);
        };
    }

    /**
     * 函数式编辑接收消息
     *
     * @return
     */
    @Bean
    public Consumer<String> sms() {
        return message -> {
            log.info("接收的普通消息为：{}", message);
        };
    }
}
