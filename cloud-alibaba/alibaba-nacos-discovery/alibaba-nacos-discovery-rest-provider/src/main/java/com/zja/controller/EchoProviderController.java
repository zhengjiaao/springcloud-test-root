package com.zja.controller; /**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-20 13:12
 * @Since:
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping
public class EchoProviderController {

    @GetMapping(value = "/echo/provider/{string}")
    public String echo(@PathVariable String string) {
        System.out.println("provider println : " + string);
        return "Hello Nacos Discovery " + string;
    }

}
