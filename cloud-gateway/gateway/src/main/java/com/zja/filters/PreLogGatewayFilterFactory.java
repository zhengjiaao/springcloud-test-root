package com.zja.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-03 10:46
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：自定义过滤器工厂-记录访问日志
 *    过滤器工厂必须以GatewayFilterFactory结尾，这是Spring Cloud Gateway的约定
 */
@Slf4j
@Component
public class PreLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        // 使用lambda表达式来创建GatewayFilter的实例，实际就是匿名内部类的简写
        return (exchange, chain) -> {
            // 通过config获取配置的参数
            log.info("配置参数：{}, {}", config.getName(), config.getValue());

            // 修改request，可以添加一些header什么的
            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .header("X-GatewayHeader","A","B")
                    .build();

            // 打印访问的接口地址
            String path = modifiedRequest.getURI().getPath();
            log.info("访问的接口为：{}", path);

            // 修改exchange
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(modifiedRequest).build();

            // 传递给下一个过滤器处理
            return chain.filter(modifiedExchange);
        };
    }
}
