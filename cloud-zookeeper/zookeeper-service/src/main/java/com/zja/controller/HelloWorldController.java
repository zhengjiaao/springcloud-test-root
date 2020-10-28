package com.zja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-22 9:46
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RestController
public class HelloWorldController {
    @GetMapping("v1/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("v1/helloworld")
    public String helloworld() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World!";
    }
}
