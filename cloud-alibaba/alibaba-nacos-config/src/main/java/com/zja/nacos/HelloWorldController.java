package com.zja.nacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前提开启: spring.cloud.nacos.refresh-enabled=true
 * 使自动刷新nacos配置注解 @RefreshScope 失效
 * 对象 MyAttributes.class 会自动刷新，不需要加 @RefreshScope注解
 *
 */
//@RefreshScope  //自动刷新nacos配置
@RestController
public class HelloWorldController {

    /**
     * 对象类配置，只要spring.cloud.nacos.refresh-enabled=true，就会自动刷新，、
     * 不需要在其使用的类上加 @RefreshScope 注解
     */
    @Autowired
    private MyAttributes myAttributes;

    @GetMapping("hello/v1")
    public Object hello() {
        return myAttributes;
    }
}
