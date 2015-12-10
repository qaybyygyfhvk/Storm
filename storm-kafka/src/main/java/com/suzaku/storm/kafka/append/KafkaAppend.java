package com.suzaku.storm.kafka.append;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import kafka.producer.Producer;

/**
 * Description: KafkaAppend
 * Author: 文超
 * Create: 2015-11-25 23:32
 */

public class KafkaAppend extends AppenderBase<ILoggingEvent> {

    private String topic;
    private String zookeeperHost;
    private Producer<String, String> producer;
    

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {

    }
}
