package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author: zhengja
 * @since: 2023/07/06 9:48
 */
@SpringBootApplication
public class RabbitConsumerAApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumerAApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RabbitConsumerAApplication.class);
    }
}