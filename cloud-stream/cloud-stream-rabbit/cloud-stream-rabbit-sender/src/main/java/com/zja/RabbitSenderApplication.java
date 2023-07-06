package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author: zhengja
 * @since: 2023/07/06 9:58
 */
@SpringBootApplication
public class RabbitSenderApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RabbitSenderApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RabbitSenderApplication.class);
    }
}