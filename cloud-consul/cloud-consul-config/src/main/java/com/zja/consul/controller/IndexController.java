/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-28 9:29
 * @Since:
 */
package com.zja.consul.controller;

import com.zja.consul.config.StudentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RefreshScope 注解 会实时更新 StudentConfig 和 @Value("${name}") 配置内容
 */
@RefreshScope // 自动更新 @Value("${name}")
@RestController
public class IndexController {

    @Value("${name}")
    private String name;

    @Autowired
    private StudentConfig studentConfig;

    /**
     * http://127.0.0.1:8080/name
     */
    @RequestMapping("/name")
    public String name() {
        return name;
    }

    /**
     * http://127.0.0.1:8080/student
     */
    @RequestMapping("/student")
    public String studentConfig() {
        return studentConfig.toString();
    }

}
