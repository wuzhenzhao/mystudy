package com.wuzz.demo.integratedway1.rabbitconfig;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 19:32
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.rabbitconfig.RabbitConfig
 */
@Configuration
public class RabbitConfig {

    //1.定义三个交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("DIRECT_EXCHANGE");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("TOPIC_EXCHANGE");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("FANOUT_EXCHANGE");
    }

    //2.定义四个队列
    @Bean
    public Queue firstQueue() {
        return new Queue("FIRST_QUEUE");
    }

    @Bean
    public Queue secondQueue() {
        return new Queue("SECOND_DQUEUE");
    }

    @Bean
    public Queue thirdQueue() {
        return new Queue("THIRD_DQUEUE");
    }

    @Bean
    public Queue fourthQueue() {
        return new Queue("FOURTH_DQUEUE");
    }

    //3.定义四个绑定关系
    @Bean
    public Binding bindFirst(@Qualifier("firstQueue") Queue queue,
                             @Qualifier("directExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("wuzz.test");
    }

    @Bean
    public Binding bindSecond(@Qualifier("secondQueue") Queue queue,
                              @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("*.wuzz.*");
    }

    @Bean
    public Binding bindThird(@Qualifier("thirdQueue") Queue queue,
                             @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindFourth(@Qualifier("fourthQueue") Queue queue,
                              @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
}
