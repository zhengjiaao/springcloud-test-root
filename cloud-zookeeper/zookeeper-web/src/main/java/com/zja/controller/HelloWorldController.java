package com.zja.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zja.service.feign.HelloWorldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-22 9:52
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class HelloWorldController {

    @Resource
    private HelloWorldService helloWorldService;

    @GetMapping("v1/hello")
    public String hello() {
        return helloWorldService.hello();
    }

    @GetMapping("v1/helloworld")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    //@HystrixCommand  //开启全局默认Fallback
    public String helloworld() {
        //int age = 10/0;  // 程序异常
        return helloWorldService.helloworld();
    }

    public String paymentTimeOutFallbackMethod() {
        return "消费者-对方系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

}
