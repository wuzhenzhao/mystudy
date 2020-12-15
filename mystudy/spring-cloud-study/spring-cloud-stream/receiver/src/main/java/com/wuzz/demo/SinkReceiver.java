package com.wuzz.demo;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/4/22 11:18
 * @since 1.0
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(value = {PositionSink.class})
public class SinkReceiver {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(PositionSink.INPUT)
    public void receive(String payload) {
        logger.info("Received Message: " + payload);
    }
}

