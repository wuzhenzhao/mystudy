package com.wuzz.demo.customer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/4/22 14:49
 * @since 1.0
 **/
public interface SinkSender {

    @Output(Source.OUTPUT)
    MessageChannel output();

    @Input(Sink.INPUT)
    SubscribableChannel input();
}
