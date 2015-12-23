package com.suzaku.storm.kafka.append;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.suzaku.storm.kafka.log.Formatter;
import com.suzaku.storm.kafka.log.MessageFormatter;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Description: KafkaAppend
 * Author: 文超
 * Create: 2015-11-25 23:32
 */

public class KafkaAppend extends AppenderBase<ILoggingEvent> {

    private String topic;
    private String zookeeperHost;
    private Producer<String, String> producer;
    private Formatter formatter;

    public String getTopic () {
        return topic;
    }

    public void setTopic (String topic) {
        this.topic = topic;
    }

    public String getZookeeperHost () {
        return zookeeperHost;
    }

    public void setZookeeperHost (String zookeeperHost) {
        this.zookeeperHost = zookeeperHost;
    }

    public Formatter getFormatter () {
        return formatter;
    }

    public void setFormatter (Formatter formatter) {
        this.formatter = formatter;
    }


    @Override
    public void start () {
        if (formatter == null) {
            formatter = new MessageFormatter();
        }
        super.start();
        Properties properties = new Properties();
        properties.put("zk.connect", zookeeperHost);
        properties.put("Serializer.class", "kafka.serializer.StringEncoder");
        ProducerConfig config = new ProducerConfig(properties);
        producer = new Producer<String, String>(config);
    }

    @Override
    public void stop () {
        super.stop();
        producer.close();
    }

    @Override
    protected void append (ILoggingEvent iLoggingEvent) {
        String payload = formatter.format(iLoggingEvent);
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, payload);
        producer.send(data);
    }

    public static void main (String[] args) {
        // 只做测试用
        Properties properties = new Properties();
        properties.put("zk.connect", "IP:2181");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        ProducerConfig config = new ProducerConfig(properties);
        Producer producer = new Producer(config);
        String payload = String.format("abc%s", "test");
        KeyedMessage<String, String> data = new KeyedMessage<String, String>("test-topic", payload);
        producer.send(data);
    }
}
