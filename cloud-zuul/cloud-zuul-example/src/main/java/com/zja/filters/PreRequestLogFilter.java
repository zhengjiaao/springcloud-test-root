package com.zja.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-11-03 13:57
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：自定义过滤器-请求日志记录
 */
@Slf4j
public class PreRequestLogFilter extends ZuulFilter {

    /**
     * Filter Types:
     * PRE Filter：在请求路由到目标之前执行。一般用于请求认证、负载均衡和日志记录。
     * ROUTING Filter：处理目标请求。这里使用Apache HttpClient或Netflix Ribbon构造对目标的HTTP请求。
     * POST Filter：在目标请求返回后执行。一般会在此步骤添加响应头、收集统计和性能数据等。
     * ERROR Filter：整个流程某块出错时执行
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    //表示相同Type的Filter的执行顺序
    @Override
    public int filterOrder() {
        return 0;
    }


    //Filter 启动和关闭自定义过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        // 打印访问的接口地址
        log.info("访问的接口为：{}  {}", request.getRequestURL() , request.getMethod());
        return null;
    }
}
