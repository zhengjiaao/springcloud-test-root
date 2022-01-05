/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-05 9:22
 * @Since:
 */
package com.zja.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1) //值小的先执行
public class HelloWorldApplicationRunner  implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("HelloWorldApplicationRunner: " + "Hello, World!");
    }
}
