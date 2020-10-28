package com.zja.service.feign.fallback;

import com.zja.service.feign.HelloWorldService;
import org.springframework.stereotype.Component;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-23 10:42
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：Hystrix熔断：快速失败机制，快速失败的处理类
 */
@Component
public class HelloWorldServiceFallback implements HelloWorldService {
    @Override
    public String hello() {
        return "服务端没有启动！";
    }
    @Override
    public String helloworld() {
        return "服务端没有启动！";
    }
}
