/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-18 12:43
 * @Since:
 */
package com.zja;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

/**
 * 基于内存的任务
 */
@EnableTask
@EnableBatchProcessing
@SpringBootApplication
public class TaskJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskJpaApplication.class, args);
    }
}
