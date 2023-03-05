package com.jhy.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//自定义过滤器
//功能:：请求过来的url中必须携带参数company_name且值为beike才能访问
@Component
public class MyTestGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {

        return ((exchange, chain) -> {
            System.out.println("进入自定义过滤器");
            String companyName = exchange.getRequest().getQueryParams().getFirst("company_name");
            //参数匹配上，放行
            if(config.getValue().equals(companyName)){
                return chain.filter(exchange);
            }else {
                //参数不匹配
                exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                return exchange.getResponse().setComplete();
            }
        });
    }

    public static class Config{

        private String companyName;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }
}