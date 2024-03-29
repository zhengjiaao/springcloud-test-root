package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SentinelZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelZuulApplication.class, args);
    }
}
