package com.zja.controller;

import com.zja.entity.MyAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-22 9:52
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RefreshScope  //自动刷新nacos配置
@RestController
public class HelloWorldController {

    @Autowired
    private MyAttributes myAttributes;

    /**
     * <a href="http://localhost:8080/v1/hello">...</a>
     */
    @GetMapping("v1/hello")
    public Object hello() {
        return myAttributes;
    }

}
