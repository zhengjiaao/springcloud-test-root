/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-25 17:06
 * @Since:
 */
package com.zja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.util.function.Consumer;

/**
 *
 */
@RestController
@RequestMapping
public class StreamConsumerController {

    @Autowired
    private StreamBridge streamBridge;

    /**
     *  向声明的source 发送
     **/
    @PostMapping("/toStream")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delegateToSupplier(@RequestBody String body) {
        System.out.println("Sending " + body);
        streamBridge.send("toStream-out-0", body);
    }

    /**
     *  直接向消费者发送  如果发送的 消费者topic 不存在会自动创建
     **/
    @PostMapping("/toLog")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delegateToConsumer(@RequestBody String body) {
        System.out.println("Sending " + body);
        streamBridge.send("log-topic", body);
    }

    /**
     *  消息消费者
     * 	可直接写为 Consumer<Message<Person>>,通过message类型可以接受更多header参数
     **/
    @Bean
    public Consumer<Person> log() {
        return person -> {
            System.out.println("Received: " + person);
            throw new RuntimeException("异常");
        };
    }

    @Bean
    public Consumer<Person> log2() {
        return person -> {
            System.out.println("Received2: " + person);
        };
    }
//	@Bean
//	public Function<Person, String> log() {
//		return person -> {
//			System.out.println("Received: " + person);
//			return person.getName();
//		};
//	}


    public static class Person {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return this.name;
        }
    }
}
