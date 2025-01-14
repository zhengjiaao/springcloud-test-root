/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-24 18:01
 * @Since:
 */
package com.zja.rabbit;

import com.zja.model.entity.User;
import com.zja.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 用户消费者管理
 *
 * @author: zhengja
 * @since: 2023/07/24 18:01
 */
@Slf4j
@Component
public class UserRabbitConsumer {

    @Autowired
    UserService userService;

    @Bean
    public Consumer<User> addUser() {
        return request -> {
            log.info("已接收: {}", request);

            //同一个系统操作，会出现死循环，这里就不演示了
            //userService.save(request);
        };
    }

    @Bean
    public Consumer<String> deleteUser() {
        return request -> {
            log.info("已接收: {}", request);
            List<String> params = Arrays.stream(request.split(","))
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            //同一个系统操作，会出现死循环，这里就不演示了
            //userService.deleteById(params.get(0));
        };
    }

}
