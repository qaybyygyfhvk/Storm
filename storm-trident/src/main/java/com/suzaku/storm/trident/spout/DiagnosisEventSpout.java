package com.suzaku.storm.trident.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Fields;
import com.suzaku.storm.trident.DefaultCoordinator;
import storm.trident.spout.ITridentSpout;

import java.util.Map;

/**
 * Description: DiagnosisEventSpout
 * Author: 文超
 * Create: 2015-11-15 16:13
 */

public class DiagnosisEventSpout implements ITridentSpout<Long> {

    private SpoutOutputCollector collector;
    private BatchCoordinator<Long> coordinator = new DefaultCoordinator();
    private Emitter<Long> emitter = new DiagnosisEventEmitter();

    public BatchCoordinator<Long> getCoordinator(String s, Map map, TopologyContext topologyContext) {
        return coordinator;
    }

    public Emitter<Long> getEmitter(String s, Map map, TopologyContext topologyContext) {
        return emitter;
    }

    public Map getComponentConfiguration() {
        return null;
    }

    public Fields getOutputFields() {
        return new Fields("event");
    }
}
