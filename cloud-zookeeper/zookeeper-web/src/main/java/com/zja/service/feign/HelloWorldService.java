package com.zja.service.feign;

import com.zja.service.feign.fallback.HelloWorldServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-22 10:03
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：OpenFeign 调用应用的接口
 */
@Component
// 服务器降级：熔断-服务宕机处理
//@FeignClient(name = "springcloud-zookeeper-service", configuration = FeignClientConfig.class,fallback = HelloWorldServiceFallback.class)
@FeignClient(name = "springcloud-zookeeper-service", fallback = HelloWorldServiceFallback.class)
public interface HelloWorldService {

    @GetMapping("v1/hello")
    String hello();

    @GetMapping("v1/helloworld")
    String helloworld();
}
