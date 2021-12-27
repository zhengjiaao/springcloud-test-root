/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-18 12:46
 * @Since:
 */
package com.zja.task.ram;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 只在启动成功以后调用一次
 *
 * Spring 容器加载完后，才执行 run 方法，并且整个应用生命周期只会执行一次
 * 因为 Spring 容器加载完后，才执行 run 方法，所以我们可以在 CommandLineRunner 接口里注入其他依赖（例如 @Autowired 注入），并且使用它们
 *
 * 在idea Program arguments 输入启动时参数：Mr.Zheng Cx330
 */
@Component
@Order(2) //值小的先执行
public class MyCommandLineRunner implements CommandLineRunner {

    /**
     * 项目启动成功，自动执行此方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- CommandLineRunner in Application...");
        System.out.println("run args length=" + args.length + ", args=" + Arrays.toString(args));
    }
}
