/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-21 13:29
 * @Since:
 */
package com.zja.feign;

import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ConsumerFallBackService implements ConsumerService{

    @Override
    public String searchRepo(String queryStr) {
        return "进入兜底方法---ConsumerFallBackService";
    }
}
