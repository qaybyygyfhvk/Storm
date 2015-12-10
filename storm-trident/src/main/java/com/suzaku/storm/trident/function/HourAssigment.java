package com.suzaku.storm.trident.function;

import com.suzaku.storm.trident.model.DiagnosisEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: HourAssigment
 * Author: 文超
 * Create: 2015-11-15 18:03
 */

public class HourAssigment extends BaseFunction {


    private static final long serialVersionUID = -4786736916209806730L;
    private Logger logger = LoggerFactory.getLogger(HourAssigment.class);

    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        DiagnosisEvent event = (DiagnosisEvent) tridentTuple.getValue(0);
        String city = (String) tridentTuple.getValue(1);
        long time = event.time;
        long hourSinceEpoch = time / 1000 / 60 / 60;
        logger.debug("key = [{}:{}]", city, hourSinceEpoch);
        String key = city + ":" + event.diagnosisCode + ":" + hourSinceEpoch;
        List<Object> values = new ArrayList<Object>();
        values.add(hourSinceEpoch);
        values.add(key);
        tridentCollector.emit(values);
    }
}
