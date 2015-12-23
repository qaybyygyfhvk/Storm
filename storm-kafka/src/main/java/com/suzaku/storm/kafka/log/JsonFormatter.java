package com.suzaku.storm.kafka.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alibaba.fastjson.JSONObject;

/**
 * Description: JsonFormatter
 * Author: diwenchao
 * Create: 2015-12-23 11:39
 */
public class JsonFormatter implements Formatter {

//    public static final String QUOTE = "\"";
//    public static final String COLON = ";";
//    public static final String COMMA = ",";

    /**
     * 格式化日志
     *
     * @param event
     *         日志
     * @return
     */
    public String format (ILoggingEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        JSONObject json = new JSONObject();
        json.put("level", event.getLevel().levelStr);
        json.put("logger", event.getLoggerName());
        json.put("timestamp", event.getTimeStamp());
        json.put("message", event.getFormattedMessage());
        return json.toJSONString();
    }
}
