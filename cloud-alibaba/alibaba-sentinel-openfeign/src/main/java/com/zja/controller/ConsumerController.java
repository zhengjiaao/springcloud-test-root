/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-21 13:33
 * @Since:
 */
package com.zja.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zja.feign.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * @SentinelResource注解的blockHandler只处理sentinel控制台的错误，不能处理程序错误，程序错误会走fallback
     * @param queryStr
     *  blockHandler指定的方法，参数里必须要有BlockException blockException
     * @return
     */
    @GetMapping(value = "/searchrepo/{queryStr}")
    @SentinelResource(value = "fallback", blockHandler = "blockSearchRepo")
    public Object getPayment(@PathVariable("queryStr") String queryStr) {
        return consumerService.searchRepo(queryStr);
    }

    public Object blockSearchRepo(@PathVariable("queryStr") String queryStr, BlockException blockException) {
        return "进入sentinel BlockHandler方法";
    }
}
