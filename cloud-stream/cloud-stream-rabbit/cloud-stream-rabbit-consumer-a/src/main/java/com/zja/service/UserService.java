/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-25 11:19
 * @Since:
 */
package com.zja.service;

import com.zja.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author: zhengja
 * @since: 2023/07/25 11:19
 */
@Slf4j
@Service
public class UserService {

    @Bean
    public Consumer<User> addUserEvent() {
        return request -> {
            log.info("已接收: {}", request);

            // 入库
            // userService.save(request);
        };
    }

    @Bean
    public Consumer<String> updateUserEvent() {
        return request -> {
            log.info("已接收: {}", request);
            List<String> params = Arrays.stream(request.split(","))
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            // 更新
            // userService.updateById(params.get(0), params.get(1));
        };
    }

    @Bean
    public Consumer<String> deleteUserEvent() {
        return request -> {
            log.info("已接收: {}", request);
            List<String> params = Arrays.stream(request.split(","))
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            // 删除
            // userService.deleteById(params.get(0));
        };
    }

}
