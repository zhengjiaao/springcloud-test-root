package com.zja.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-23 10:48
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@Configuration
public class FeignClientConfig {

    /**
     * Feign 默认重试策略：
     *  period=100 发起当前请求的时间间隔，单位毫秒
     *  maxPeriod=1000 发起当前请求的最大时间间隔，单位毫秒
     *  maxAttempts=5 最多请求次数，包括第一次
     */
    @Bean
    public Retryer feignRetryer() {
        //默认：100 1 5
        return new Retryer.Default(100, SECONDS.toMillis(1), 1);
    }
}
