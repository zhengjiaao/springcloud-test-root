/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-28 13:20
 * @Since:
 */
package com.zja.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    /**
     * http://127.0.0.1:8080/hello
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
