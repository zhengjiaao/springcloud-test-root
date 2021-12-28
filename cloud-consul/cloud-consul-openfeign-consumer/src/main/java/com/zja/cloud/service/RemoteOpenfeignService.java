package com.zja.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "remoteservice", url = "http://localhost:18002/springboot-test-remoteservice/")
public interface RemoteOpenfeignService {

    @GetMapping("/rest/v1/get/hystrix")
    Object getHystrix();
}
