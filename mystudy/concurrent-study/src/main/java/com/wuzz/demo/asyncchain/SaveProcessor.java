package com.wuzz.demo.asyncchain;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/26 13:53
 * @since 1.0
 **/
public class SaveProcessor extends AbstractProcessor implements RequestProcessor {

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();

    private RequestProcessor nextProcessor;

    public SaveProcessor() {
    }

    public SaveProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = requests.take();
                request.setName(request.getName() + ",hello");
                System.out.println("begin save request info:" + request + requests.size());
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
