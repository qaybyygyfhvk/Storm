package com.suzaku.storm.word;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.*;

/**
 * Description: ReportBolt
 * Author: 文超
 * Create: 2015-11-14 15:32
 */

public class ReportBolt extends BaseRichBolt {

    private HashMap<String, Long> counts ;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        counts = new HashMap<String, Long>();
    }

    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        Long count = tuple.getLongByField("count");
        counts.put(word, count);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public void cleanup() {
        System.out.println("--- FINAL COUNT ---");
        List<String> keys = new ArrayList<String>();
        keys.addAll(counts.keySet());
        Collections.sort(keys);
        for(String key : keys ){
            System.out.println(key + " : " + counts.get(key));
        }
        System.out.println("-----------------");
    }
}
