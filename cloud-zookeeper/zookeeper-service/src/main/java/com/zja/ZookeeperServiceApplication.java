package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.zookeeper.discovery.ConditionalOnZookeeperDiscoveryEnabled;

@SpringBootApplication
@EnableDiscoveryClient //向注册中心(zookeeper)上注册服务
public class ZookeeperServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperServiceApplication.class, args);
    }
}
