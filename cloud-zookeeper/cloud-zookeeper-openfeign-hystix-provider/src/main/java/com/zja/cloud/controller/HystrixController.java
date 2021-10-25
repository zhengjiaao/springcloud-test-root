package com.zja.cloud.controller;

import com.zja.cloud.service.HystrixOpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-30 10:07
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RestController
public class HystrixController {

    @Autowired
    HystrixOpenFeignService hystrixOpenFeignService;

    //正常请求
    @GetMapping("provider/ok/{id}")
    public String Request_Ok(@PathVariable("id") Integer id){
        return hystrixOpenFeignService.provider_Ok(id);
    }

    //请求超时
    @GetMapping("provider/timeout/{id}")
    public String Request_TimeOut(@PathVariable("id") Integer id){
        return hystrixOpenFeignService.provider_TimeOut(id);
    }

    //熔断：熔断打开-->熔断关闭--->熔断半开：当多次错误，再输入正确的值，会发现还是错误信息，当正确率高了，慢慢的熔断半开-->熔断关闭
    @GetMapping("provider/circuitbreaker/{id}")
    public String providerCircuitBreaker(@PathVariable("id") Integer id){
        return hystrixOpenFeignService.providerCircuitBreaker(id);
    }

}
