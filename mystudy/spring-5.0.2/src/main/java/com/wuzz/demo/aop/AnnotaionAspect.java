package com.wuzz.demo.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/20
 * Time: 10:35
 * Description 描述:
 */
@Component
//声明这是一个切面Bean
@Aspect
public class AnnotaionAspect {
    private final static Logger log = LoggerFactory.getLogger(AnnotaionAspect.class);

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.wuzz.demo.aop.web..*(..))")
    public void aspect() {
    }

    /*
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint 切入点对象,可以没有该参数
     */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        System.out.println(signature.getName());
        //AOP代理类的名字
        System.out.println(signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap =  new HashMap();
        while (enumeration.hasMoreElements()){
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if(obj.length > 0) {
            System.out.println("请求的参数信息为："+str);
        }
        log.info("before 通知" + joinPoint);
    }

    //配置后置通知,使用在方法aspect()上注册的切入点
    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        log.info("after 通知" + joinPoint);
    }

    //配置环绕通知,使用在方法aspect()上注册的切入点
    @Around("aspect()")
    public Object around(JoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            Object proceed = ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            log.info("around 通知" + joinPoint + "\tUse time : " + (end - start) + " ms!");
            return proceed;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            log.info("around 通知" + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
        }
        return null;
    }

    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint) {
        log.info("afterReturn 通知" + joinPoint);
    }

    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        log.info("afterThrow 通知" + joinPoint + "\t" + ex.getMessage());
    }
}
