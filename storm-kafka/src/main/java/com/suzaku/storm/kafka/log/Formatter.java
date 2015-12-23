package com.suzaku.storm.kafka.log;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Description: Formatter
 * Author: diwenchao
 * Create: 2015-12-23 10:37
 */
public interface Formatter {

    /**
     * 格式化日志
     *
     * @param event
     *         日志
     * @return
     */
    String format (ILoggingEvent event);
}
