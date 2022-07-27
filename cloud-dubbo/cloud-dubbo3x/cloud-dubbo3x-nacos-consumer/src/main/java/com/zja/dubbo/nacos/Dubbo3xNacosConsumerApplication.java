/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-06-27 14:50
 * @Since:
 */
package com.zja.dubbo.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDubbo // 默认启用
//@ImportResource(value={"classpath:dubbo/provider.xml"})
@SpringBootApplication
public class Dubbo3xNacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo3xNacosConsumerApplication.class, args);
    }

}
