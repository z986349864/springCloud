package com.gatway.gatway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        List<String> auth = queryParams.get("auth");
        for (String s : auth) {
            // 3.校验
            if ("admin".equals(s)) {
                // 放行
                return chain.filter(exchange);
            }
            // 4.拦截
            // 4.1.禁止访问，设置状态码
        }
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        // 4.2.结束处理
        return exchange.getResponse().setComplete();
    }
}
