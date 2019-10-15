package com.wuzz.demo.auto.configuration.demo.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class CacheImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //可以获取到注解都元数据，即我们定义的 exclude
        Map<String,Object> attributes=
                annotationMetadata.getAnnotationAttributes(EnableDefineService.class.getName());
        //动态注入bean :自己去实现判断逻辑实现动态配置

        return new String[]{CacheService.class.getName()}; //我这返回的是一个固定的CacheService
    }
}
