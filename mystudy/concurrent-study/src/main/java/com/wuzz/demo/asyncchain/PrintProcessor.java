package com.wuzz.demo.asyncchain;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/26 13:50
 * @since 1.0
 **/
public class PrintProcessor extends AbstractProcessor implements RequestProcessor {

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();

    private RequestProcessor nextProcessor;

    public PrintProcessor() {
    }

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = requests.take();
                request.setName("wuzz");
                System.out.println("print data:" + request.getName() + requests.size());
                if (nextProcessor != null) {
                    nextProcessor.processRequest(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //处理请求
    public void processRequest(Request request) {
        requests.add(request);
    }

}
