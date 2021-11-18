package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka-server 注册中心服务端地址 ： http://localhost:16001/eureka/
 */
@SpringBootApplication
@EnableEurekaServer //注解启动一个服务注册中心提供给其他应用进行对话
public class EurekaServerApplication16001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication16001.class, args);
    }
}
