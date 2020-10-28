package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //建议使用，默认带有自我注册功能
@EnableFeignClients //(basePackages = "com.zja.service.feign") // 开启 OpenFeign
@EnableHystrix
public class ZookeeperWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperWebApplication.class, args);
    }
}
