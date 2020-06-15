package com.wuzz.demo.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/15 14:17
 * @since 1.0
 **/
public class CustomerFourFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Customer Four filter Begins");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
