package com.wuzz.demo;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    private final static String GATEWAY_ROUTE_KEY="gateway_dynamic_route";

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitionList=new ArrayList<>();
        redisTemplate.opsForHash().values(GATEWAY_ROUTE_KEY).stream().forEach(route->{
            routeDefinitionList.add(JSON.parseObject(route.toString(),RouteDefinition.class));
        });
        return Flux.fromIterable(routeDefinitionList);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            redisTemplate.opsForHash().put(GATEWAY_ROUTE_KEY,routeDefinition.getId(),JSON.toJSONString(routeDefinition));
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id->{
            if(redisTemplate.opsForHash().hasKey(GATEWAY_ROUTE_KEY,id)){
                redisTemplate.opsForHash().delete(GATEWAY_ROUTE_KEY,id);
                return Mono.empty();
            }
            return Mono.defer(()->Mono.error(new Exception("routeDefinition not found:"+routeId)));
        });
    }
}
