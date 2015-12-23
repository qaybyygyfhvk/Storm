package com.suzaku.storm.kafka.log;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Description: MessageFormatter
 * Author: diwenchao
 * Create: 2015-12-23 10:38
 */
public class MessageFormatter implements Formatter {

    /**
     * 格式化日志
     *
     * @param event
     *         日志
     * @return
     */
    public String format (ILoggingEvent event) {
        return event.getFormattedMessage();
    }
}
