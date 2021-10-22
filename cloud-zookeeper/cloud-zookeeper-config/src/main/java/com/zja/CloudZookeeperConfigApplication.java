package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDiscoveryClient //启动 zookeeper
@SpringBootApplication
public class CloudZookeeperConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudZookeeperConfigApplication.class, args);
    }
}
