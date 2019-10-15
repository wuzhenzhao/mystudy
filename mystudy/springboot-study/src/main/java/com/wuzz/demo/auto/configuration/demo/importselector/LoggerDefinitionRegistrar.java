package com.wuzz.demo.auto.configuration.demo.importselector;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

public class LoggerDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //增加相关动态校验注入，我这里写死
        Class beanClass=LoggerService.class;
        RootBeanDefinition beanDefinition=new RootBeanDefinition(beanClass);
        String beanName= StringUtils.uncapitalize(beanClass.getSimpleName());
        beanDefinitionRegistry.registerBeanDefinition(beanName,beanDefinition);

    }
}
