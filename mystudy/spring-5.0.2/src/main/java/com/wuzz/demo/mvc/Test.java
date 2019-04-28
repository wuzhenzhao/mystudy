package com.wuzz.demo.mvc;

import com.wuzz.demo.mvc.annotation.WuzzController;
import com.wuzz.demo.mvc.annotation.WuzzRequestMapping;
import com.wuzz.demo.mvc.controller.TestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/26
 * Time: 14:26
 * Description 描述:
 */
public class Test {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);
    public static void main(String[] args) {
        //  Class
        Class clazz = TestController.class;
        //判断这个类是否存在 @WuzzController
        if (clazz.isAnnotationPresent(WuzzController.class)) {
            LOGGER.info(clazz.getName() + "被标记为controller");
            String path = "";
            //判断clazz是否存在注解@WuzzRequestMapping
            if (clazz.isAnnotationPresent(WuzzRequestMapping.class)) {
                //取出注解的值 放入path
                WuzzRequestMapping reqAnno = (WuzzRequestMapping) clazz.getAnnotation(WuzzRequestMapping.class);
                path = reqAnno.value().toString();
            }

            Method[] ms = clazz.getMethods();//拿到控制类所有公开方法遍历
            for (Method method : ms) {
                //如果不存在该注解  就进入下一轮
                if (!method.isAnnotationPresent(WuzzRequestMapping.class)) {
                    continue;
                }
                LOGGER.info("方法"+method.getName()+",映射的对外路径：" + path + method.getAnnotation(WuzzRequestMapping.class).value().toString());
            }
        }

    }
}
