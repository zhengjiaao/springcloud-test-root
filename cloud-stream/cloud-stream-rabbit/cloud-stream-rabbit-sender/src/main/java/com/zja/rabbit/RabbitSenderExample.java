/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-13 14:44
 * @Since:
 */
package com.zja.rabbit;

import com.zja.model.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

/**
 * @author: zhengja
 * @since: 2023/07/13 14:44
 */
@Service
@Slf4j
public class RabbitSenderExample {

//    @Autowired
//    RabbitTemplate template;

    @Autowired
    StreamBridge streamBridge;

    // 消息队列
    private static final String ADD_COUPON_EVENT = "";


    public void sendCoupon(UserRequest userRequest) {
        log.info("sent: {}", userRequest);
        streamBridge.send(ADD_COUPON_EVENT, userRequest);
    }
}
