package com.zja.predicates;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-03 10:29
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：自定义 路由谓词工厂
 *       路由谓词工厂必须以RoutePredicateFactory结尾，这是Spring Cloud Gateway的约定
 */
@Slf4j
@Component
public class TimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeBetweenConfig> {

    public TimeBetweenRoutePredicateFactory() {
        super(TimeBetweenConfig.class);
    }

    /**
     * 实现谓词判断的方法
     */
    @Override
    public Predicate<ServerWebExchange> apply(TimeBetweenConfig config) {
        return exchange -> {
            LocalTime start = config.getStart();
            LocalTime end = config.getEnd();

            // 判断当前时间是否为允许访问的时间段内
            LocalTime now = LocalTime.now();
            return now.isAfter(start) && now.isBefore(end);
        };
    }

    /**
     * 控制配置类（TimeBetweenConfig）属性和配置文件中配置项（TimeBetween）的映射关系
     */
    @Override
    public List<String> shortcutFieldOrder() {
        /*
         * 例如我们的配置项是：TimeBetween=上午9:00, 下午5:00
         * 那么按照顺序，start对应的是上午9:00；end对应的是下午5:00
         **/
        return Arrays.asList("start", "end");
    }

    // 最后需要在配置文件中启用该路由谓词工厂
    // 并且需要禁止gateway通过服务发现组件转发请求到其他的微服务
    // 要禁止gateway通过服务发现组件转发请求到其他的微服务，
    // 是因为开启该配置项的话会导致我们自定义的路由谓词工厂不生效。
    // 不生效也是有原因的，开启该配置项会令Gateway优先将请求按照该配置项进行转发，
    // 那么我们自定义的路由就不会生效
}
