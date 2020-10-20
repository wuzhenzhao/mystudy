package com.wuzz.demo.service;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.HashMap;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/4/16 10:00
 * @since 1.0
 **/
public class HelloObservableCommand extends HystrixObservableCommand<String> {
    private RestTemplate restTemplate;

    private HashMap map;

    public HelloObservableCommand(RestTemplate restTemplate, HashMap paramMap) {
        super(com.netflix.hystrix.HystrixObservableCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("helloObservableCommand")).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)));
        this.restTemplate = restTemplate;
        this.map = paramMap;
    }

    @Override
    protected Observable<String> construct() {
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
}
