package com.zja.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-02 12:56
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RefreshScope  //自动刷新nacos配置
@RestController
public class ConfigController {

    //nacos 配置
    @Value("${name}")
    private String name;

    @GetMapping("name")
    public String getname(){
        return name;
    }
}
