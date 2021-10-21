/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-21 14:16
 * @Since:
 */
package com.zja;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping
public class RestProviderController {

    @GetMapping("/get")
    public String get() {
        return "恭喜你，请求成功！";
    }

    @GetMapping("/get/{str}")
    public String getStr(@PathVariable("str") String str) {
        return "恭喜你，请求成功: " + str;
    }

    @GetMapping("/user/info")
    public String userInfo() {
        return "用户信息";
    }

    @GetMapping("/cat/hungry")
    public String catHungry() {
        return "猫饿了！";
    }

    @GetMapping("/cat/thirsty")
    public String catThirsty() {
        return "猫渴了......";
    }

}
