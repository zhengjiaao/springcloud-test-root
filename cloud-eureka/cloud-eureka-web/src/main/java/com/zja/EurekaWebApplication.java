package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-09 13:25
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@SpringBootApplication
//@EnableEurekaClient  //不推荐，默认没有自我注册功能
@EnableDiscoveryClient //建议使用，默认带有自我注册功能
@EnableFeignClients // 开启 OpenFeign
public class EurekaWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaWebApplication.class,args);
    }
}
