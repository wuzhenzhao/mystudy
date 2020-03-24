package com.wuzz.demo.chain_of_responsibility.chain;

import com.wuzz.demo.chain_of_responsibility.Member;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/23 18:01
 * @since 1.0
 **/
public abstract class Handler {
    //下一个调用链
    protected Handler next;

    public void next(Handler next) {
        this.next = next;
    }

    public abstract void doHandler(Member member);

}
