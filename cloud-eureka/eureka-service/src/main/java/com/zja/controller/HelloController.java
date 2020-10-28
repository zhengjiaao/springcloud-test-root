package com.zja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-09 14:51
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RequestMapping
@RestController("ServiceHelloController")
public class HelloController {
    @GetMapping("v1/hello")
    public String get(@RequestParam String name){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "name: "+name;
    }
}
