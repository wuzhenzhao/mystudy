package com.wuzz.demo.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.wuzz.demo.ClientService;
import com.wuzz.demo.service.HelloCollapseCommand;
import com.wuzz.demo.service.HelloCollapseService;
import com.wuzz.demo.service.HelloCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/10
 * Time: 15:10
 * Description 描述:
 */
@RestController
public class RibbonController {

    //  private static final String REST_URL_PREFIX="http://localhost:8001"; 单机版
    //集群的时候  需要配置该服务在eureka里注册的名字
    private static final String REST_URL_PREFIX = "http://cloud-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloCollapseService helloCollapseService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/hystrix/feign")
    public String feign() throws InterruptedException {
        return clientService.hello("11115");
    }


    @RequestMapping(value = "/batchHello")
    public List<String> batchHello() throws InterruptedException, ExecutionException {
        //需要开启HystrixRequest上下文，合并请求和缓存必须开启
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        List<String> result = new ArrayList<>();
        HelloCollapseCommand bc1 = new HelloCollapseCommand(helloCollapseService, "6");
        HelloCollapseCommand bc2 = new HelloCollapseCommand(helloCollapseService, "9");
        HelloCollapseCommand bc3 = new HelloCollapseCommand(helloCollapseService, "5");
        HelloCollapseCommand bc4 = new HelloCollapseCommand(helloCollapseService, "8");
        Future<String> q1 = bc1.queue();
        Future<String> q2 = bc2.queue();
        Future<String> q3 = bc3.queue();
        String result1 = q1.get();
        String result2 = q2.get();
        String result3 = q3.get();
        Thread.sleep(3000);
        Future<String> q4 = bc4.queue();
        String result4 = q4.get();

        return result;
    }

    @RequestMapping(value = "/annotationBatchHello")
    public String find(String id) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> stringFuture = helloCollapseService.find(id);
        Future<String> stringFuture2 = helloCollapseService.find("6");
        return stringFuture.get() + "======" + stringFuture2.get();
    }


    //同步
    @HystrixCommand(commandProperties = {
            //HystrixCommandProperties 类中包含配置信息所有
            //开启熔断
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //最小请求数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            //熔断5秒
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            //10秒内 最少请求 5次。若百分比超过 50 则触发熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
    }, fallbackMethod = "processHystrix_Get")//熔断机制
    @RequestMapping(value = "/hello")
    public String get(Long id) {
        Map map = new HashMap<>();
        map.put("id", id);
        return restTemplate.getForObject(REST_URL_PREFIX + "/hello?id={id}", String.class, map);
    }

    @HystrixCommand(fallbackMethod = "timeoutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
    })
    @GetMapping("/hystrix/timeout")
    public String queryTimeout() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/timeout", String.class);
    }

    public String timeoutFallback() {
        return "timeOut Hystrix";
    }

    /**
     * 信号量隔离实现
     * 不会使用Hystrix管理的线程池处理请求。使用容器（Tomcat）的线程处理请求逻辑。
     * 不涉及线程切换，资源调度，上下文的转换等，相对效率高。
     * 信号量隔离也会启动熔断机制。如果请求并发数超标，则触发熔断，返回fallback数据。
     * commandProperties - 命令配置，HystrixPropertiesManager中的常量或字符串来配置。
     * execution.isolation.strategy - 隔离的种类，可选值只有THREAD（线程池隔离）和SEMAPHORE（信号量隔离）。默认是THREAD线程池隔离。
     * 设置信号量隔离后，线程池相关配置失效。
     * execution.isolation.semaphore.maxConcurrentRequests - 信号量最大并发数。默认值是10。常见配置500~1000。
     * 如果并发请求超过配置，其他请求进入fallback逻辑。
     *   
     */
    @HystrixCommand(fallbackMethod = "semaphoreFallback",
            commandProperties = {
                    // 信号量隔离
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),
                    // 信号量最大并发数
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "5"),
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED, value = "true"),
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "3000")
            }
    )
    @GetMapping("/hystrix/semaphore")
    public String semaphore() {
        Map map = new HashMap<>();
        map.put("id", "1");
        return restTemplate.getForObject(REST_URL_PREFIX + "/hello?id={id}", String.class, map);
    }

    @HystrixCommand(
//            groupKey = "order-service", commandKey = "queryOrder", threadPoolKey = "order-service",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),//线程池大小
                    @HystrixProperty(name = "maxQueueSize", value = "100"),//最大队列长度
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),//线程存活时间
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15")//拒绝请求
            },
            commandProperties = {
                    // 隔离
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD"),
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED, value = "true"),
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "3000"),
            },
            fallbackMethod = "semaphoreFallback")
    @GetMapping("/hystrix/thread")
    public String thread() {
        Map map = new HashMap<>();
        map.put("id", "1");
        return restTemplate.getForObject(REST_URL_PREFIX + "/hello?id={id}", String.class, map);
    }

    public String semaphoreFallback() {
        System.out.println("semaphore Hystrix");
        return "semaphore Hystrix";
    }

    public String threadFallback() {
        return "thread Hystrix";
    }

    //继承HystrixCommand的实现
    @RequestMapping(value = "/helloCommand")
    public String helloCommand(Long id) {
        HashMap map = new HashMap<>();
        map.put("id", id);
        //同步
        String result = new HelloCommand(restTemplate, map).execute();
        //异步
//        Future<String> result = new HelloCommand(restTemplate, map).queue();
        //响应式执行方式
//        Observable<String> hotObserve = new HelloCommand(restTemplate, map).observe();
//        Observable<String> coldObservable = new HelloCommand(restTemplate, map).toObservable();
        return result;
    }

    //异步
    @HystrixCommand(fallbackMethod = "getByidAsyncFailed")//熔断机制
    @RequestMapping(value = "/getByidAsync")
    public String getUserByidAsync(String id) {
        HashMap map = new HashMap<>();
        map.put("id", id);
        AsyncResult<String> asyncResult = new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject(REST_URL_PREFIX + "/hello?id={id}", String.class, map);
            }

            @Override
            public String get() {
                return invoke();
            }
        };
        return asyncResult.get();
    }

    //HystrixObservableCommand
    //EAGER 是该参数的模式值， 表示使用 observe ()执行方式。
    @HystrixCommand(fallbackMethod = "getByidAsyncFailed", observableExecutionMode = ObservableExecutionMode.EAGER)
//    //表示使用 toObservable() 执行方式。
//    @HystrixCommand(fallbackMethod = "getByidAsyncFailed",observableExecutionMode = ObservableExecutionMode.LAZY)
    @RequestMapping(value = "/helloHystrixObservableCommand")
    public Observable<String> helloHystrixObservableCommand(String id) {
        HashMap map = new HashMap<>();
        map.put("id", id);
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        String string = restTemplate.getForObject("http://cloud-provider/hello?id={id}", String.class, map);
                        observer.onNext(string);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    //消费端可以调用服务发现
    @RequestMapping(value = "/discovery")
    public Object discovery() {

        return restTemplate.getForObject(REST_URL_PREFIX + "/discovery", Object.class);
    }

    public String processHystrix_Get(Long id) {
        return "hello Hystrix";
    }

    public String getByidAsyncFailed(String id) {
        return "getByidAsyncFailed Hystrix";
    }
}
