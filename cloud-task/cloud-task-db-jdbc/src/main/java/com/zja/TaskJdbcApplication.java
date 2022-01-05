package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

/**
 * 基于内存的任务
 */
@EnableTask
//@EnableBatchProcessing
@SpringBootApplication
public class TaskJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskJdbcApplication.class, args);
    }
}
