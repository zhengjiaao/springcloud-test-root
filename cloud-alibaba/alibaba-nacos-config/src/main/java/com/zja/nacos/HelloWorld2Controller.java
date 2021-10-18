/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-18 17:22
 * @Since:
 */
package com.zja.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开启 spring.cloud.nacos.refresh-enabled=true，默认 true
 *
 * 如果使用@Value("")，必须在类上加 @RefreshScope才会生效,不加或加在其使用的方法上使用不生效
 */
@RefreshScope  //自动刷新nacos配置，生效
@RestController
public class HelloWorld2Controller {

    /**
     * 必须要在类上加 @RefreshScope注解，才会自动刷新配置
     */
    @Value("${attributes.config}")
    private String config;

//    @RefreshScope //不生效
    @GetMapping("hello/v2")
    public Object hello() {
        return config;
    }
}
