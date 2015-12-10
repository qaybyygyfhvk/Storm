package com.suzaku.storm.trident.function;

import com.suzaku.storm.trident.filter.DiseaseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

/**
 * Description: DispatchAlert
 * Author: 文超
 * Create: 2015-11-15 18:42
 */

public class DispatchAlert extends BaseFunction {


    private static final long serialVersionUID = 657634154753197957L;

    private Logger logger = LoggerFactory.getLogger(DiseaseFilter.class);

    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        String alert = (String) tridentTuple.getValue(0);
        logger.error("ALERT RECEIVED [{}]", alert);
        logger.error("Dispatch the national guard !");
        System.exit(0);
    }
}
