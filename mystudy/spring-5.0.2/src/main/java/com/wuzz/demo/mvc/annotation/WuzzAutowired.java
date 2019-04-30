package com.wuzz.demo.mvc.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WuzzAutowired {
    String value() default "";
}
