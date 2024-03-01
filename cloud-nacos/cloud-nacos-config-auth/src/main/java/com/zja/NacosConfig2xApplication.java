package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author: zhengja
 * @since: 2023/08/15 15:28
 */
@SpringBootApplication
public class NacosConfig2xApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfig2xApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NacosConfig2xApplication.class);
    }
}