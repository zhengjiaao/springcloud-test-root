package com.zja.cloud.service;

import org.springframework.stereotype.Component;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-30 10:09
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：OpenFeign Hystrix 熔断：当服务器宕机时，返回下方的接口的数据
 */
@Component
public class HystrixOpenFeignServiceFallback implements HystrixOpenFeignService {

    @Override
    public String provider_Ok(Integer id) {
        return "-----HystrixOpenFeignServiceFallback fall provider_Ok ,o(╥﹏╥)o";
    }

    @Override
    public String provider_TimeOut(Integer id) {
        return "-----HystrixOpenFeignServiceFallback fall provider_TimeOut ,o(╥﹏╥)o";
    }
}
