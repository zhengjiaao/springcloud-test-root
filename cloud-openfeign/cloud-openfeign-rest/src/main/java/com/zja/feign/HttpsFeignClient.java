/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-25 9:53
 * @Since:
 */
package com.zja.feign;

import com.zja.config.IgnoreSSLCheckConfiguration;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Https 示例
 * 默认：支持 http、https 协议
 *
 * 添加：IgnoreSSLCheckConfigurationc.lass  忽略SLL校验
 * 注意：Feign 不支持自己创建的无效SLL(过不了校验，直接IP上SSL)
 * 错误信息：sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
 */
@Component
@FeignClient(name = "remote-web-https", url = "https://192.168.2.128:19001", configuration = IgnoreSSLCheckConfiguration.class)
public interface HttpsFeignClient {

    //get

    @GetMapping(value = "/get")
    @ApiOperation(value = "get-无参数", notes = "返回字符串")
    String get();

    @GetMapping(value = "/get/{path}")
    @ApiOperation(value = "get-路径参数")
    String getPath1(@PathVariable("path") String path);

}
