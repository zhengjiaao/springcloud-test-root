/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-25 16:05
 * @Since:
 */
package com.zja.send;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 *
 */
@Service
@Slf4j
public class StreamConsumerService {

    /**
     * 直接装配一个桥 用来连接rabbit或者kafka
     */
    @Autowired
    private StreamBridge streamBridge;

    public void sendMethod() {
        String message = UUID.randomUUID().toString();
        //这里说明一下这个 streamBridge.send 方法的参数 第一个参数是exchange或者topic 就是主题名称
        //默认的主题名称是通过
        //输入:    <方法名> + -in- + <index>
        //输出:    <方法名> + -out- + <index>
        //这里我们接收的时候就要用send方法 参数是consumer<String>接收  详情看8802的controller
        //consumer的参数类型是这里message的类型
        streamBridge.send("send-in-0", message);
        System.out.println("************发送了message：" + message);
    }



    /**
     * 采用StreamBridge的发送方式
     *
     * @param message 　短消息
     * @link https://docs.spring.io/spring-cloud-stream/docs/3.1.3/reference/html/spring-cloud-stream.html#_binding_and_binding_names
     */
    /*public void sendSms(String message) {
        streamBridge.send(MessageConstant.SMS_MESSAGE_OUTPUT, message);
    }*/
}
