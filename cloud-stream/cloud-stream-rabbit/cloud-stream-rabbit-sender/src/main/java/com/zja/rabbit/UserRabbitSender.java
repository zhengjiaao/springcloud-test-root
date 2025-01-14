/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-24 17:30
 * @Since:
 */
package com.zja.rabbit;

import com.zja.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

/**
 * 用户发送者管理
 *
 * @author: zhengja
 * @since: 2023/07/24 17:30
 */
@Slf4j
@Component
public class UserRabbitSender {

    @Autowired
    private StreamBridge streamBridge;

    /**
     * spring.cloud.stream.bindings.addUser-out-0.destination="add-user-exchange"
     * spring.cloud.stream.bindings.addUser-int-0.destination="add-user-exchange"
     */
    private static final String ADD_USER_EVENT = "add-user-exchange";

    /**
     * spring.cloud.stream.bindings.updateUser-out-0.destination="update-user-exchange"
     * spring.cloud.stream.bindings.updateUser-int-0.destination="update-user-exchange"
     */
    private static final String UPDATE_USER_EVENT = "add-user-exchange";

    /**
     * spring.cloud.stream.bindings.deleteUser-out-0.destination="delete-user-exchange"
     * spring.cloud.stream.bindings.deleteUser-int-0.destination="delete-user-exchange"
     */
    private static final String DELETE_USER_EVENT = "delete-user-exchange";

    // 发送消息

    public void addUser(User user) {
        log.info("sent addUser event: {}", user);
        streamBridge.send(ADD_USER_EVENT, user);
    }

    public void updateUser(User user) {
        log.info("sent updateUser event: {}", user);
        streamBridge.send(UPDATE_USER_EVENT, user);
    }

    public void deleteUser(String userId) {
        log.info("sent deleteUser event: userId={}", userId);
        streamBridge.send(DELETE_USER_EVENT, userId);
    }

}
