/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-25 9:53
 * @Since:
 */
package com.zja.feign;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign Https 示例
 * 默认：支持 http、https 协议
 */
@Component
@FeignClient(name = "remote-github", url = "https://developer.aliyun.com")
public interface HttpsFeignClient {

    /**
     * https://developer.aliyun.com/search?q=docker
     * @param q
     * @return
     */
    @GetMapping(value = "/search")
    @ApiOperation(value = "get-有参数", notes = "返回字符串")
        //UnknownContentTypeException: Could not extract response: no suitable HttpMessageConverter found for response type [class java.lang.Object] and content type [text/html;charset=utf-8]
//    Object getSearch(@ApiParam("搜索内容") @RequestParam("q") String q);
    String getSearch(@ApiParam("搜索内容") @RequestParam("q") String q);

}
