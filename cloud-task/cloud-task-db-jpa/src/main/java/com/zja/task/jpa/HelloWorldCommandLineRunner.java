/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-18 12:46
 * @Since:
 */
package com.zja.task.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 只在启动成功以后调用一次,比如初始化一些数据
 */
@Component
public class HelloWorldCommandLineRunner implements CommandLineRunner {

    /**
     * 项目启动成功，自动执行此方法
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("HelloWorldCommandLineRunner ");
    }
}
