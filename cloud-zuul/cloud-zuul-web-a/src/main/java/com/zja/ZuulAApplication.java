package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZuulAApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulAApplication.class, args);
    }
}
