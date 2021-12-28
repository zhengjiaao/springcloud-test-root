package com.zja.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling       //可选的。自动拉取 consul 配置，配合 @RefreshScope
@SpringBootApplication
public class ConsulConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConfigApplication.class, args);
    }

}
