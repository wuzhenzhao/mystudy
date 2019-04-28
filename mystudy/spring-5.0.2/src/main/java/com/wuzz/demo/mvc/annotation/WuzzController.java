package com.wuzz.demo.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//表示注解运行在哪里 这里表示只能注解再类上面
@Retention(RetentionPolicy.RUNTIME)//表示注解的(生命周期)哪来出现
public @interface WuzzController {
}
