package com.zja.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-15 16:01
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RestController
public class TestController {

    @Value("${config.test}")
    private String configTest;

    @GetMapping("get")
    public Object get() {
        return configTest;
    }
}
