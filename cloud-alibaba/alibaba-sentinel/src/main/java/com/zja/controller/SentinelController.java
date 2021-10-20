/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-20 17:03
 * @Since:
 */
package com.zja.controller;

import com.zja.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping
public class SentinelController {

    @Autowired
    private SentinelService sentinelService;

    @GetMapping(value = "/hello")
//    @SentinelResource("hello") //注释用于识别资源是速率受限还是降级
    public String hello() {
        return "Hello Sentinel";
    }

    @GetMapping(value = "/ip")
    public Object ip() {
        return sentinelService.getIpInfo();
    }

}
