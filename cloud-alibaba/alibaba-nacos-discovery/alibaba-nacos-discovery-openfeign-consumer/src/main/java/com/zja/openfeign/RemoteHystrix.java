/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-20 15:49
 * @Since:
 */
package com.zja.openfeign;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class RemoteHystrix implements RemoteClient{
    @Override
    public String helloNacos(String str) {
        return "请求超时了";
    }
}
