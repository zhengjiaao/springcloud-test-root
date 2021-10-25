package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayAApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAApplication.class, args);
    }
}
