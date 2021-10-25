package com.zja.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-30 10:08
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：OpenFeign 请求接口
 */
//@FeignClient(name = "zk-openfeign-hystix-provider")
@FeignClient(name = "zk-openfeign-hystix-provider",fallback = HystrixOpenFeignServiceFallback.class)
public interface HystrixOpenFeignService {
    @GetMapping("provider/ok/{id}")
    String provider_Ok(@PathVariable("id") Integer id);

    @GetMapping("provider/timeout/{id}")
    String provider_TimeOut(@PathVariable("id") Integer id);
}
