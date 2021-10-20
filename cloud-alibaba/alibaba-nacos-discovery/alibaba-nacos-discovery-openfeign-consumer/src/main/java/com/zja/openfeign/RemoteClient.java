/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-20 15:48
 * @Since:
 */
package com.zja.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 */
@FeignClient(name = "nacos-openfeign-provider", fallback = RemoteHystrix.class)
public interface RemoteClient {

    @GetMapping("/nacos/hello/{str}")
    String helloNacos(@PathVariable(value="str")  String str);
}
