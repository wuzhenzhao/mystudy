package com.wuzz.demo.proxy.customjdkproxy;

import java.lang.reflect.Method;

public class $Proxy0 implements com.wuzz.demo.proxy.staticproxy.Person {
    MyInvocationHandler h;

    public $Proxy0(MyInvocationHandler h) {
        this.h = h;
    }

    public void findLove() {
        try {
            Method m = com.wuzz.demo.proxy.staticproxy.Person.class.getMethod("findLove", new Class[]{});
            this.h.invoke(this, m, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void findJob() {
        try {
            Method m = com.wuzz.demo.proxy.staticproxy.Person.class.getMethod("findJob", new Class[]{});
            this.h.invoke(this, m, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
