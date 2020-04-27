package com.wuzz.demo.asyncchain;

/**
 * @description: 模仿zookeeper的 processchain，进行的异步化调用链的示例
 * @author: wuzhenzhao
 * @time 2020/4/26 13:54
 * @since 1.0
 **/
public class Demo {
    PrintProcessor printProcessor;

    protected Demo() {
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
    }

    private void doTest(Request request) {
        printProcessor.processRequest(request);
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setName("Mic");
        new Demo().doTest(request);
    }
}
