package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author: zhengja
 * @since: 2023/07/06 9:49
 */
@SpringBootApplication
public class RabbitConsumerBApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumerBApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RabbitConsumerBApplication.class);
    }
}