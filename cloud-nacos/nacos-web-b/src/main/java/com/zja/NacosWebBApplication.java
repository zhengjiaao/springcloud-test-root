package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosWebBApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosWebBApplication.class, args);
    }
}
