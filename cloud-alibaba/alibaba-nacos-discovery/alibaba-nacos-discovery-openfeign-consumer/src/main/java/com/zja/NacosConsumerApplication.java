/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-20 15:37
 * @Since:
 */
package com.zja;

import com.zja.openfeign.RemoteClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 */
@EnableDiscoveryClient  //注解表明是一个 Nacos 客户端
@EnableFeignClients //开启 OpenFeign
@SpringBootApplication
public class NacosConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }

    @RestController
    @RequestMapping
    public class NacosConsumerController {

        @Resource
        private RemoteClient remoteClient;

        @GetMapping("/feign")
        public String feignTest() {
            return remoteClient.helloNacos("666");
        }
    }

}
