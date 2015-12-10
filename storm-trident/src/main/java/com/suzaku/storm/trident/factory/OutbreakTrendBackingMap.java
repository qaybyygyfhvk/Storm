package com.suzaku.storm.trident.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.state.map.IBackingMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: OutbreakTrendBackingMap
 * Author: 文超
 * Create: 2015-11-15 18:18
 */

public class OutbreakTrendBackingMap implements IBackingMap<Long> {

    private Logger logger = LoggerFactory.getLogger(OutbreakTrendBackingMap.class);
    Map<String, Long> storage = new ConcurrentHashMap<String, Long>();

    public List<Long> multiGet(List<List<Object>> list) {
        List<Long> values = new ArrayList<Long>();
        for (List<Object> key : list) {
            Long value = storage.get(key.get(0));
            if (value == null) {
                values.add(new Long(0));
            } else {
                values.add(value);
            }
        }
        return values;
    }

    public void multiPut(List<List<Object>> list, List<Long> list1) {
        for (int i = 0; i < list.size(); i++) {
            logger.info("Persisting [{}] ==> [{}]", list.get(i).get(0), list1.get(i));
            storage.put((String) list.get(i).get(0), list1.get(i));
        }
    }
}
