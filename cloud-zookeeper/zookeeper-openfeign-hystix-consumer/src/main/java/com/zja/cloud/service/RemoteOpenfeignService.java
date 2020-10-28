package com.zja.cloud.service;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-05-07 15:59
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "remoteservice",url = "http://localhost:18002/springboot-test-remoteservice/")
public interface RemoteOpenfeignService {

    @GetMapping("/rest/v1/get/hystrix")
    Object getHystrix();
}
