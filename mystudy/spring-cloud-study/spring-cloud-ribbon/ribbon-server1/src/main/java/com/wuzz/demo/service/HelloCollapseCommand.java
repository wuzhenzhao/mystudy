package com.wuzz.demo.service;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/4/16 11:12
 * @since 1.0
 **/
//通过继承HystrixCollapser实现请求合并器
public class HelloCollapseCommand extends HystrixCollapser<List<String>, String, String> {

    private HelloCollapseService helloCollapseService;

    private String id;

    public HelloCollapseCommand(HelloCollapseService helloCollapseService, String id) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("helloCollapseCommand"))
                .andCollapserPropertiesDefaults(
                        HystrixCollapserProperties.Setter()
                                .withTimerDelayInMilliseconds(100)));
        this.helloCollapseService = helloCollapseService;
        this.id = id;
    }

    @Override
    public String getRequestArgument() {
        return id;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, String>> collapsedRequests) {
        List<String> userids = new ArrayList<>(collapsedRequests.size());
        userids.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new HelloBatchCommand(helloCollapseService, userids);
    }

    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, String>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<String, String> collapsedRequest : collapsedRequests) {
            String user
                    = batchResponse.get(count++);
            collapsedRequest.setResponse(user);
        }
    }
}
