package com.zja.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-03 10:55
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：自定义全局过滤器- 打印访问的接口路径以及打印该接口的访问耗时
 */
@Slf4j
public class MyGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        log.info("[MyGlobalFilter] 访问的接口：{}", path);

        long start = System.currentTimeMillis();
        return chain.filter(exchange)
                // then的内容会在过滤器返回的时候执行，即最后执行
                .then(Mono.fromRunnable(() ->
                        log.info("[ {} ] 接口的访问耗时：{} /ms",
                                path, System.currentTimeMillis() - start))
                );
    }
}

// 最后需要使该全局过滤器生效，方法有很多种：
// 可以直接在该类上加@Component注解
// 也可以通过代码配置（@Bean）
// 个人比较倾向于使用一个专门的配置类去实例化这些全局过滤器并交给Spring容器管理
