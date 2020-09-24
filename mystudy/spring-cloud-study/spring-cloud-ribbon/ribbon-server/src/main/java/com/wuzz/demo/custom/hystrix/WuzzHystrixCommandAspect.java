package com.wuzz.demo.custom.hystrix;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@Component
@Aspect
public class WuzzHystrixCommandAspect {
    //线程池的处理，基于这个线程池的处理统计可以达到 THREAD 资源限流
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    //注解切点
    @Pointcut(value = "@annotation(com.wuzz.demo.custom.hystrix.WuzzHystrixCommand)")
    public void pointCut() {
    }

    //环绕通知
    @Around(value = "pointCut()&&@annotation(hystrixCommand)")
    public Object doPointCut(ProceedingJoinPoint joinPoint, WuzzHystrixCommand hystrixCommand) throws InterruptedException, ExecutionException, TimeoutException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int timeout = hystrixCommand.timeout();
        //前置的判断逻辑
        Future future = executorService.submit(() -> {
            try {
                return joinPoint.proceed(); //执行目标方法
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
        Object result;
        try {// 使用 future 来实现超时
            result = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            future.cancel(true);
            // ？
            if (StringUtils.isBlank(hystrixCommand.fallback())) {
                throw e;
            }
            //调用fallback
            result = invokeFallback(joinPoint, hystrixCommand.fallback());
        }
        return result;
    }

    private Object invokeFallback(ProceedingJoinPoint joinPoint, String fallback) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取被代理的方法的参数和Method
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();
        //得到fallback方法
        try {
            Method fallbackMethod = joinPoint.getTarget().getClass().getMethod(fallback, parameterTypes);
            fallbackMethod.setAccessible(true);
            //完成反射调用
            return fallbackMethod.invoke(joinPoint.getTarget(), joinPoint.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
