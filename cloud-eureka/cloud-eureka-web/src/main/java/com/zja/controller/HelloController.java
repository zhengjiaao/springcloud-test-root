package com.zja.controller;

import com.zja.service.ServiceOpenFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-09 14:51
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@RequestMapping
@RestController
public class HelloController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceOpenFeign serviceOpenFeign;

    @GetMapping("v2/hello/{name}")
    public Object get(@PathVariable("name") String name) {
        //第一种方式：RestTemplate调用
        /*RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://localhost:8762/v1/hello?name=" + name, String.class);
        System.out.println("响应结果： " + forObject.toString());

        //第二种方式：Ribbon 实现负载均衡
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-service");

        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"v1/hello?name="+name;
        String object = restTemplate.getForObject(url, String.class);
        System.out.println("响应结果2： " + object.toString());*/

        //第二种方式：RestTemplate+Ribbon 实现负载均衡 ，推荐使用
        String forObject = restTemplate.getForObject("http://EUREKA-SERVICE/v1/hello?name=" + name, String.class);
        System.out.println("restTemplate+Ribbon 负载均衡响应结果： " + forObject.toString());
        return forObject;
    }

    @GetMapping("v3/hello/{name}")
    public String gethello(@PathVariable("name") String name) {
        String hello = serviceOpenFeign.hello(name);
        System.out.println("OpenFeign响应结果: "+hello);
        return hello;
    }
}
