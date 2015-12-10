package com.suzaku.storm.trident.factory;

import backtype.storm.task.IMetricsContext;
import storm.trident.state.State;
import storm.trident.state.StateFactory;

import java.util.Map;

/**
 * Description: OutbreakTrendFactory
 * Author: 文超
 * Create: 2015-11-15 18:15
 */

public class OutbreakTrendFactory implements StateFactory {
    public State makeState(Map map, IMetricsContext iMetricsContext, int i, int i1) {
        return new OutbreakTrendState(new OutbreakTrendBackingMap());
    }
}
