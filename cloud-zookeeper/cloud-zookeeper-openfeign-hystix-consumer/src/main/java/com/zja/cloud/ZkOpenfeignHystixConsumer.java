package com.zja.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
@EnableFeignClients //(basePackages = "com.zja.service.feign") // 开启 OpenFeign
@EnableHystrix  // 开启服务器降级/熔断机制
public class ZkOpenfeignHystixConsumer {

    public static void main(String[] args) {
        SpringApplication.run(ZkOpenfeignHystixConsumer.class, args);
    }

}
