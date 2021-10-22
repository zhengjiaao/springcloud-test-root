/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-21 15:47
 * @Since:
 */
package com.zja.consumer;

import com.zja.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping
public class UserController {

    @DubboReference
    private UserService userService;

    @GetMapping("get/userinfo")
    public Object getUserInfo(){
       return userService.getUserInfo();
    }
}
