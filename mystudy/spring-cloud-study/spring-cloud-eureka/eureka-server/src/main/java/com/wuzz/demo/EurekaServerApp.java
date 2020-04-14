package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@EnableEurekaServer // Eureka服务端注解
@SpringBootApplication
public class EurekaServerApp {
    private final static Logger log = LoggerFactory.getLogger(EurekaServerApp.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp.class,args);
        log.info("服务启动成功");

    }
    // 1.什么时微服务
    //      简单地说， 微服务是系统架构上的 一 种设计风格， 它的主旨是将 一 个原本独立的系统
    //      拆分成多个小型服务，这些小型服务都在各自独立的进程中运行，服务之间通过基于HTTP
    //      的RESTful API进行通信协作。
    // 2.与单体系统得区别
    //      为了解决单体系统变得庞大脯肿之后产生的难以维护的问题， 微服务架构诞生了并被
    //      大家所关注。 我们将系统中的不同功能模块拆分成多个不同的服务， 这些服务都能够独立
    //      部署和扩展。
    // 3.如何实施微服务
    //      运维的新挑战：在微服务架构中， 运维人员需要维护的进程数量会大大增加。 有条
    //      不紊地将这些进程编排和组织起来不是 一 件容易的事， 传统的运维人员往往很难适
    //      应这样的改变。 我们需要运维人员有更多的技能来应对这样的挑战， 运维过程需要
    //      更多的自动化， 这就要求运维人员具备 一 定的开发能力来编排运维过程并让它们能
    //      自动运行起来。
    //      • 接口的 一 致性：虽然我们拆分了服务， 但是业务逻辑上的依赖并不会消除， 只是从
    //      单体应用中的代码依赖变为了服务间的通信依赖。 而当我们对原有接口进行了 一 些
    //      修改， 那么交互方也需要协调这样的改变来进行发布， 以保证接口的正确调用。 我
    //      们需要更完善的接口和版本管理， 或是严格地遵循开闭原则。
    //      • 分布式的复杂性：由于拆分后的各个微服务都是独立部署并运行在各自的进程内，
    //      它们只能通过通信来进行协作， 所以分布式环境的问题都将是微服务架构系统设计
    //      时需要考虑的重要因素， 比如网络延迟、 分布式事务、 异步消息等。
    //      服务组件化
    //      组件， 是 一 个可以独立更换和升级的单元。 就像 PC 中的 CPU、 内存、 显卡、 硬盘 一
    //      样， 独立且可以更换升级而不影响其他单元。
    // 4.为什么选择 spring cloud
    //      Spring Cloud的出现，可以说是对微服务架构的巨大 支持和强有力的技术后盾。它不像
    //      我们之前所列举的框架那样， 只是解决微服务中的某 一 个问题， 而是 一 个解决微服务架构
    //      实施的综合性解决框架， 它整合了诸多被广泛实践和证明过的框架作为实施的基础部件，
    //      又在该体系基础上创建了 一 些 非常优秀的边缘组件。
}
