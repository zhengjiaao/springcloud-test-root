package com.zja.Impl;

import com.zja.dto.User;
import com.zja.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-02 9:40
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@DubboService
public class HelloServiceImpl implements HelloService {

    @Override
    public String getName(){
        return "李四";
    }

    @Override
    public User getUser(){
        User user = new User();
        user.setName("张三");
        user.setAge(24);
        return user;
    }

}
