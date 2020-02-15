package com.wuzz.demo.webapp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    /*** 上下文对象实例 ***/
    private static ApplicationContext applicationContext;


    /**
     * 获取 applicationContext
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }


    /**
     * 通过name获取 Bean.
     *
     * @param name bean name
     * @return bean
     */
    public Object getBean(String name) {
        return this.applicationContext.getBean(name);
    }


    /**
     * 通过class获取Bean.
     *
     * @param clazz bean class
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> clazz) {
        return this.applicationContext.getBean(clazz);
    }


    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  bean name
     * @param clazz bean class
     * @param <T>
     * @return bean
     */
    public <T> T getBean(String name, Class<T> clazz) {
        return this.applicationContext.getBean(name, clazz);
    }

}
