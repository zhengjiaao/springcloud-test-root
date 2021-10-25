package com.zja.config;

import com.zja.filters.MyGlobalFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-03 10:57
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@Slf4j
@Configuration
public class FilterConfig {

    @Bean
    // 该注解用于指定过滤器的执行顺序，数字越小越优先执行
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter myGlobalFilter(){
        log.info("create myGlobalFilter...");
        return new MyGlobalFilter();
    }
}
