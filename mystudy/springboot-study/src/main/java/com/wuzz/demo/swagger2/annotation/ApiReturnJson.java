package com.wuzz.demo.swagger2.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/** 
* @ClassName: ApiReturnJson 
* @Description: 返回对象的定义 (描述这个类的作用) 
* @author TangCai
* @date 2019年2月22日 下午4:56:33  
*/
  	
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiReturnJson {
    String key();  //对象名称
    ApiReturnJsonPro[] value(); //对象属性值
}
