package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SentinelOpenfeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelOpenfeignApplication.class, args);
    }
}
