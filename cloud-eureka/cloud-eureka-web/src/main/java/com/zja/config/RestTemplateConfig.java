package com.zja.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-09 16:40
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@Component
public class RestTemplateConfig {

    /**
     * 默认rest方式开启 负载均衡功能
     * @return
     */
    @Bean
    @LoadBalanced  //Ribbon 负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
