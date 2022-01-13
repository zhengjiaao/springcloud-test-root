package com.zja.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/login
 */
@SpringBootApplication
public class SecurityOAuth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityOAuth2ServerApplication.class, args);
    }
}
