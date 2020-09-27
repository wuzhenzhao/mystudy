package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
//Order
@Component
public class WuzzDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<WuzzDefineGatewayFilterFactory.WuzzConfig>{

    private static final String NAME_KEY="name";

    Logger logger= LoggerFactory.getLogger(WuzzDefineGatewayFilterFactory.class);

    public WuzzDefineGatewayFilterFactory() {
        super(WuzzConfig.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY);
    }

    @Override
    public GatewayFilter apply(WuzzConfig config) {
        //Filter pre  post
        return ((exchange,chain)->{
            logger.info("[pre] Filter Request, name:"+config.getName());
            //TODO
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                //TODO
                logger.info("[post]: Response Filter");
            }));
        });
    }

    public static class WuzzConfig{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


