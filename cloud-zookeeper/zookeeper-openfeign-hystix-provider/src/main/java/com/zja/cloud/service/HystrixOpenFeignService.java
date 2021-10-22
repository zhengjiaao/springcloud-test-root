package com.zja.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-30 10:08
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@Service
public class HystrixOpenFeignService {

    public String provider_Ok(Integer id){
        return "线程池:  " + Thread.currentThread().getName() + "  provider_Ok,id:  " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    //服务降级：当多并发调用请求接口或者业务出现异常，服务承受能力不足时会触发服务降级
    @HystrixCommand(fallbackMethod = "provider_TimeOut_Handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String provider_TimeOut(Integer id){
        //int age = 10/0;  /模拟出异常
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + "provider_TimeOut,id:  " + id + "\t" + "O(∩_∩)O哈哈~" + "  耗时(秒): ";
    }

    private String provider_TimeOut_Handler(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "  80系统繁忙或者运行报错，请稍后再试,id:  " + id + "\t" + "o(╥﹏╥)o";
    }


    /*******************服务熔断********************/

    @HystrixCommand(fallbackMethod = "providerCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数到达 10次
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期，是否尝试服务可用
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸 60%
    })
    public String providerCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = String.valueOf(System.currentTimeMillis());

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    private String providerCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }

}
