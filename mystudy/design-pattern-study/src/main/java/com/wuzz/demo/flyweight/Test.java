package com.wuzz.demo.flyweight;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 19:06
 * @since 1.0
 **/
public class Test {

    public static void main(String[] args) {
        ITicket ticket = TicketFactory.queryTicket("北京西", "长沙");
        ticket.showInfo("硬座");
        ticket = TicketFactory.queryTicket("北京西", "长沙");
        ticket.showInfo("软座");
        ticket = TicketFactory.queryTicket("北京西", "长沙");
        ticket.showInfo("硬卧");
    }

}
