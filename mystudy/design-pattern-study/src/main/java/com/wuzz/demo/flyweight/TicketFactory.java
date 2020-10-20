package com.wuzz.demo.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/24 19:06
 * @since 1.0
 **/
class TicketFactory {
    private static Map<String, ITicket> sTicketPool = new ConcurrentHashMap<String,ITicket>();

    public static ITicket queryTicket(String from, String to) {
        String key = from + "->" + to;
        if (TicketFactory.sTicketPool.containsKey(key)) {
            System.out.println("使用缓存：" + key);
            return TicketFactory.sTicketPool.get(key);
        }
        System.out.println("首次查询，创建对象: " + key);
        ITicket ticket = new TrainTicket(from, to);
        TicketFactory.sTicketPool.put(key, ticket);
        return ticket;
    }
}
