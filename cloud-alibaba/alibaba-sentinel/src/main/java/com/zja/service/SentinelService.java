/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-20 17:04
 * @Since:
 */
package com.zja.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 业务逻辑处理类
 */
@Service
public class SentinelService {

    /**
     * 逻辑处理方法
     * 1. 注解表明了它需要被Sentinel监控。
     * 2. blockHandler指明了如果被Sentinel阻止了该返回啥
     * @return
     */
    @SentinelResource(value = "ip_info", blockHandler = "exceptionHandler")
    public Object getIpInfo() {
        String result = "";
        try {
            InetAddress address = InetAddress.getLocalHost();
            result = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public Object exceptionHandler(BlockException ex) {
        ex.printStackTrace();
        return "请求过于频繁";
    }

}
