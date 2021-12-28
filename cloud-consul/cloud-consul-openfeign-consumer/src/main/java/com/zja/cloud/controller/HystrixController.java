package com.zja.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zja.cloud.service.HystrixOpenFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-30 10:07
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：服务的降级/熔断
 */
@RestController
@DefaultProperties(defaultFallback = "consumer_Global_FallbackMethod")
public class HystrixController {

    @Resource
    HystrixOpenFeignService hystrixOpenFeignService;


    /**
     * 正常请求 http://127.0.0.1:8082/consumer/ok/2
     */
    @GetMapping("consumer/ok/{id}")
    public Object consumer_Ok(@PathVariable("id") Integer id) {
        return hystrixOpenFeignService.provider_Ok(id);
    }

    /**
     * 请求超时 http://127.0.0.1:8082/consumer/timeout//2
     */
    @GetMapping("consumer/timeout/{id}")
    @HystrixCommand(fallbackMethod = "consumer_TimeOut_FallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    //@HystrixCommand
    public Object consumer_TimeOut(@PathVariable("id") Integer id) {
        //int age = 10/0;
        return hystrixOpenFeignService.provider_Ok(id);
    }


    //特定类方法异常
    public String consumer_TimeOut_FallbackMethod(@PathVariable("id") Integer id) {
        return "消费者81,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    //全局fallback方法
    public String consumer_Global_FallbackMethod() {
        return "Global 全局异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
