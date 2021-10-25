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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    //get

    @GetMapping(value = "/get")
    @ApiOperation(value = "get-无参数", notes = "返回字符串")
    public Object get() {
        return httpsFeignClient.get();
    }

    @GetMapping(value = "/get/{path}")
    @ApiOperation(value = "get-路径参数")
    public Object getPath1(@PathVariable("path") String path) {
        return httpsFeignClient.getPath1(path);
    }


}
