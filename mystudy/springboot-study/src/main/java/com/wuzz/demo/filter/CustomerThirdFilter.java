package com.wuzz.demo.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/15 14:17
 * @since 1.0
 **/
//指定过滤某些路径，还需要再启动类上添加 @ServletComponentScan
// 如果想过滤所有，也可以直接使用 @Component
//@WebFilter(urlPatterns = "/wuzz/*", filterName = "customerThirdFilter")
//public class CustomerThirdFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("Customer Third filter init");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("Customer Third filter Begins");
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
