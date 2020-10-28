package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public KeyResolver uriKeyResolver(){
//        return new KeyResolver() {
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange) {
//                System.out.println(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
//                return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
//            }
//        };
//    }
}

