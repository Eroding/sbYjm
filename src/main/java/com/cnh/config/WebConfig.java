package com.cnh.config;


import com.cnh.access.AccessLimitInterceptor;
import com.cnh.access.UserArgumentResolver;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * 本来想填写项目地址的，但是发现域名没有解析到本机，所以就不先配置了,
 * 这里就先配置类来管理过滤器
 */
@Configuration
public class WebConfig  extends WebMvcConfigurationSupport {
    @Autowired
    private AccessLimitInterceptor accessLimitInterceptor;

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(accessLimitInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/static/**");
        super.addInterceptors(registry);

    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

/**
 * 添加参数解析器，就是为了把user加入到uri中，省的每次在controller中去判断有没有
 */
@Override
protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(userArgumentResolver);
}


/**
 * 以下是用elasearch
 */
/*@Bean
    public TransportClient client() throws UnknownHostException {
    InetSocketAddress node = new InetSocketAddress(
            InetAddress.getByName("localhost"),
            9300
    );
    TransportClient client = new PreBuiltTransportClient(Setting.EMPTY_PARAMS);
    client.addTransportAddress(node);
}*/



}
