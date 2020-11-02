package com.zja.controller;

import com.zja.dto.User;
import com.zja.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
public class DubboController {

    @DubboReference
    private HelloService helloService;

    @GetMapping("v1/dubbo/name")
    public String name(){
        return helloService.getName();
    }

    @GetMapping("v1/dubbo/user")
    public User user(){
        return helloService.getUser();
    }

}
