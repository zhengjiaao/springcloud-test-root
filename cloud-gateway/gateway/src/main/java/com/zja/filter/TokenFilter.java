//package com.zja.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
///**
// * Company: 上海数慧系统技术有限公司
// * Department: 数据中心
// * Date: 2020-10-26 17:52
// * Author: zhengja
// * Email: zhengja@dist.com.cn
// * Desc： Gateway统一鉴权
// */
//@Component
//public class TokenFilter implements GlobalFilter, Ordered {
//
//    /**
//     * 需要输入 http://localhost:8084/WEB/v1/hello?token=1 才能访问
//     * @param exchange
//     * @param chain
//     * @return
//     */
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
//        if (token ==null || token.isEmpty()){
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//        System.out.println("拦截...");
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return -500;
//    }
//}
