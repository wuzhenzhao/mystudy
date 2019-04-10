package com.wuzz.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class ConfigBean {
	
//	Eureka是基于REST（Representational State Transfer）服务，
//	主要以AWS云服务为支撑，提供服务发现并实现负载均衡和故障转移。
//	我们称此服务为Eureka服务。
//	Eureka提供了Java客户端组件，Eureka Client，方便与服务端的交互。
//	客户端内置了基于round-robin实现的简单负载均衡。
//	在Netflix，为Eureka提供更为复杂的负载均衡方案进行封装，
//	以实现高可用，它包括基于流量、资源利用率以及请求返回状态的加权负载均衡。

	
	/**
	 * Ribbon是Netflix发布的云中间层服务开源项目，主要功能是提供客户端负载均衡算法。
	Ribbon客户端组件提供一系列完善的配置项，如，连接超时，重试等。
	简单的说，Ribbon是一个客户端负载均衡器，
	我们可以在配置文件中列出load Balancer后面所有的机器，
	Ribbon会自动的帮助你基于某种规则(如简单轮询，随机连接等)去连接这些机器，
	我们也很容易使用Ribbon实现自定义的负载均衡算法
	 * 
	 */
//	Feign是一个声明式的Web服务客户端，使用Feign可使得Web服务客户端的写入更加方便。 
//	它具有可插拔注释支持，包括Feign注解和JAX-RS注解、Feign还支持可插拔编码器和解码器、
//	Spring Cloud增加了对Spring MVC注释的支持，
//	并HttpMessageConverters在Spring Web中使用了默认使用的相同方式。
//	Spring Cloud集成了Ribbon和Eureka，在使用Feign时提供负载平衡的http客户端。	
	
	
	
	@Bean
	@LoadBalanced // ribbon是客户端 的负载均衡工具
	//默认算法是轮询算法 核心组件IRule
	public  RestTemplate getRestTemplate() {
		
		return new RestTemplate();
	}
	
	@Bean
	public IRule myRule() { // 负载均衡算法。。。。
		
//		return new RoundRobinRule();
		return new RandomRule();
	}

}
