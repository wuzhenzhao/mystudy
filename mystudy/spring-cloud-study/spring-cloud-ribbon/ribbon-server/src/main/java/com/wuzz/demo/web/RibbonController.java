package com.wuzz.demo.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.wuzz.demo.service.HelloCollapseCommand;
import com.wuzz.demo.service.HelloCollapseService;
import com.wuzz.demo.service.HelloCommand;
import org.springframework.beans.factory.annotation.Autowired;
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
        return stringFuture.get()+"======"+stringFuture2.get();
    }


    //同步
    @HystrixCommand(fallbackMethod = "processHystrix_Get")//熔断机制
    @RequestMapping(value = "/hello")
    public String get(Long id) {
        Map map = new HashMap<>();
        map.put("id", id);
        return restTemplate.getForObject(REST_URL_PREFIX + "/hello?id={id}", String.class, map);
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
