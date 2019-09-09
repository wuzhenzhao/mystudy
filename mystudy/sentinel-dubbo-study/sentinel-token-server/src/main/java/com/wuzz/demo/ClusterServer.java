package com.wuzz.demo;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

import java.util.Collections;


/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 16:51
 * Description 描述:
 */
public class ClusterServer {

    public static void main(String[] args) throws Exception {
        ClusterTokenServer tokenServer=new SentinelDefaultTokenServer();
        ClusterServerConfigManager.loadGlobalTransportConfig(
                new ServerTransportConfig().setIdleSeconds(600).setPort(9999));
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("app-wuzz")); //设置成动态
        tokenServer.start();
    }
}
