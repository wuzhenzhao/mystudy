package com.wuzz.demo.myrule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class MyRule_ZY extends AbstractLoadBalancerRule {

	private int total = 0;
	private int currentIndex = 0;

	public Server choose(ILoadBalancer lb, Object key) {
		if (lb == null) {
			return null;
		}
		Server server = null;

		while (server == null) {
			if (Thread.interrupted()) {// 测试线程是否中断
				return null;
			}
			// 返回当前可正常服务的实例列表
			List<Server> upList = lb.getReachableServers();
			// 返回所有已知的服务实例列表，包括正常服务和停止服务的实例
			List<Server> allList = lb.getAllServers();
			//获取服务列表长度
			int serverCount = allList.size();

			if (serverCount == 0) {
				return null;
			}
			//自定义的一些算法
			if (total < 5) {
				server = upList.get(currentIndex);
				total++;
			} else {
				total = 0;
				currentIndex++;
				if (currentIndex >= upList.size()) {
					currentIndex = 0;
				}
			}
			if (server == null) {
				/*
				 * The only time this should happen is if the server list were somehow trimmed.
				 * This is a transient condition. Retry after yielding.
				 */
				Thread.yield();//线程让步
				continue;
			}

			if (server.isAlive()) {//判断服务是否活跃
				return (server);
			}

			// Shouldn't actually happen.. but must be transient or a bug.
			server = null;
			Thread.yield();
		}

		return server;

	}

	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		// TODO Auto-generated method stub

	}
}
