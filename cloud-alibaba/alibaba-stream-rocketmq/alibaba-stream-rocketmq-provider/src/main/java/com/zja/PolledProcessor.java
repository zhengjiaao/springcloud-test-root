/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-21 14:58
 * @Since:
 */
package com.zja;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.MessageChannel;

/**
 * 轮询处理器
 */
public interface PolledProcessor {

    @Input
    PollableMessageSource source();

    @Output
    MessageChannel dest();
}
