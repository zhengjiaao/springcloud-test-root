/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-18 15:36
 * @Since:
 */
package com.zja.task.jpa;

import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class TaskConfig extends DefaultTaskConfigurer {

    /**
     * 将TaskRepository存储到数据库（MySQL、oracle等）的配置
     * @param dataSource
     */
    public TaskConfig(DataSource dataSource){
        super(dataSource);
    }
}
