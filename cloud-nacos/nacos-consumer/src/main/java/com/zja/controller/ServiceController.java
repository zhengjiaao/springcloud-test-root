package com.zja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-02 12:55
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RestController
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    //根据服务获取实例信息
    @GetMapping("getInstances")
    public List<ServiceInstance> getInstances(String serviId){
        //查询指定服务的所有实例信息
        //无论使用什么做注册中心都可以使用consul、eureka、zookeeper
        return discoveryClient.getInstances(serviId);
    }

    //获取服务
    @GetMapping("getServices")
    public List<String> getServices(){
        //查询该注册中心有多少个服务
        return discoveryClient.getServices();
    }
}
