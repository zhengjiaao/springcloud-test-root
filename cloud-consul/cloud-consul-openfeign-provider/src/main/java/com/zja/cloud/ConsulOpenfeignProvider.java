package com.zja.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
@EnableHystrix  // 开启服务降级/服务熔断
public class ConsulOpenfeignProvider {

    public static void main(String[] args) {
        SpringApplication.run(ConsulOpenfeignProvider.class, args);
    }

}
