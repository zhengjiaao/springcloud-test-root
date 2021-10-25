package com.zja.cloud.controller;

import com.zja.cloud.service.RemoteOpenfeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-05-07 16:01
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RestController
public class RemoteController {

    @Resource
    RemoteOpenfeignService remoteOpenfeignService;

    @RequestMapping(value = "get/hystrix", method = RequestMethod.GET)
    public Object getHystrix() {
        return remoteOpenfeignService.getHystrix();
    }
}
