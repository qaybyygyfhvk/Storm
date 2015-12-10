package com.suzaku.storm.word;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import java.util.Map;

/**
 * Description: SentenceSpout
 * Author: 文超
 * Create: 2015-11-14 11:34
 */

public class SentenceSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;

    private String[] sentences = {
            "A Storm cluster is superficially similar to a Hadoop cluster",
            "Whereas on Hadoop you run",
            "one key difference is that a MapReduce job eventually finishes",
            "whereas a topology processes messages forever",
            "There are two kinds of nodes on a Storm cluster",
            "the master node and the worker nodes",
            "The master node runs a daemon called Nimbus",
            "Nimbus is responsible for distributing code around the cluster",
            "The supervisor listens for work assigned to its machine and starts and stops worker processes as necessary based on what Nimbus has assigned to it",
            "Each worker process executes a subset of a topology",
            "my dog has fleas",
            "i like cold beverages",
            "the dog ate my homework",
            "don't have a cow man",
            "i don't think i like fleas"
    };

    private int index = 0;

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("sentence"));
    }

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
    }

    public void nextTuple() {
        collector.emit(new Values(sentences[index]));
        index ++;
        if(index >= sentences.length){
            index = 0;
        }
        Utils.sleep(1);
    }
}
