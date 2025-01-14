package com.zja.cloud.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 * 启动类
 *
 * @swagger: <a href="http://localhost:8080/swagger-ui/index.html">...</a>
 * @author: zhengja
 * @since: 2025/01/14 13:54
 */
@SpringBootApplication
// @EnableDiscoveryClient
@EnableBinding({Source.class, Sink.class})
public class CloudRabbitApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CloudRabbitApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CloudRabbitApplication.class);
    }
}