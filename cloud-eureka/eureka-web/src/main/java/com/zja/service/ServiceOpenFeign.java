package com.zja.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-09 17:11
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：调用的是 SPRINGCLOUD-EUREKA-SERVICE 服务
 */
@FeignClient(name = "SPRINGCLOUD-EUREKA-SERVICE") //要调用的服务器名称
public interface ServiceOpenFeign {
    @GetMapping("/v1/hello")
    String hello(@RequestParam("name") String name);
}
