package com.wuzz.demo.chain_of_responsibility.builder_chain;

import com.wuzz.demo.chain_of_responsibility.Member;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 19:07
 * @since 1.0
 **/
public abstract class Handler<T> {
    protected Handler next;

    public void next(Handler next) {
        this.next = next;
    }

    public abstract void doHandler(Member member);

    public static class Builder<T> {
        private Handler<T> head;
        private Handler<T> tail;

        public Builder<T> addHandler(Handler handler) {
//            do {
            if (this.head == null) {
                this.head = this.tail = handler;
                return this;
            }
            this.tail.next(handler);
            this.tail = handler;
//            }while (false);//真正框架中，如果是双向链表，会判断是否已经到了尾部
            return this;
        }

        public Handler<T> build() {
            return this.head;
        }

    }
}
