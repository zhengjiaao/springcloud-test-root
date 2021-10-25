/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-25 9:58
 * @Since:
 */
package com.zja.controller;

import com.zja.feign.HttpsFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:18000/swagger-ui/index.html#/
 *
 * Feign默认：支持 http、https 协议
 */
@Api("Feign Https 示例")
@RestController
@RequestMapping("/feign/https")
public class HttpsFeignClientController {

    @Autowired
    private HttpsFeignClient httpsFeignClient;

    @ApiOperation("aliyun 搜索-https 示例")
    @GetMapping("/get/https/github/search")
    public Object get(@ApiParam(value = "搜索内容", defaultValue = "docker") @RequestParam("q") String q) {
        return httpsFeignClient.getSearch(q);
    }
}
