package com.suzaku.storm.trident.function;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: OutbreakDetector
 * Author: 文超
 * Create: 2015-11-15 18:35
 */

public class OutbreakDetector extends BaseFunction {

    private static final long serialVersionUID = 6138102762373499628L;
    public static final int THRESHOLD = 10000;

    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        String key = (String) tridentTuple.getValue(0);
        Long count = (Long) tridentTuple.getValue(1);
        if (count > THRESHOLD) {
            List<Object> values = new ArrayList<Object>();
            values.add("Outbreak detected for [" + key + "]!");
            tridentCollector.emit(values);
        }
    }
}
