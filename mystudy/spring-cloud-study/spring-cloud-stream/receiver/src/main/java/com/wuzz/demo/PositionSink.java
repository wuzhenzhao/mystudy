package com.wuzz.demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/12/9 12:27
 * @since 1.0
 **/
public interface PositionSink {

    String INPUT = "positionInput";

    @Input("positionInput")
    SubscribableChannel input();
}
