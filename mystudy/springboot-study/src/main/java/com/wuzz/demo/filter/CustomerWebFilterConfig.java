//package com.wuzz.demo.filter;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @description: 类功能描述
// * @author: wuzhenzhao
// * @time 2020/6/15 14:32
// * @since 1.0
// **/
//@Configuration
//public class CustomerWebConfig {
//
//    /**
//     * 注册filter
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean  bean = new FilterRegistrationBean(new CustomerSecondFilter());
//        //bean.addUrlPatterns(new String[]{"*.do","*.jsp"});
//        bean.addUrlPatterns("/wuzz/*");
//        bean.setOrder(22);//这个可以设置过滤器顺序 ，值越小 优先执行
//        return bean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean2() {
//        FilterRegistrationBean  bean = new FilterRegistrationBean(new CustomerFourFilter());
//        //bean.addUrlPatterns(new String[]{"*.do","*.jsp"});
//        bean.addUrlPatterns("/wuzz/*");
//        bean.setOrder(33);
//        return bean;
//    }
//}
