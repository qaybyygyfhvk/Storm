package com.suzaku.storm.trident.factory;

import storm.trident.state.map.NonTransactionalMap;

/**
 * Description: OutbreakTrendState
 * Author: 文超
 * Create: 2015-11-15 18:16
 */

public class OutbreakTrendState extends NonTransactionalMap<Long> {

    protected OutbreakTrendState(OutbreakTrendBackingMap outbreakTrendBackingMap) {
        super(outbreakTrendBackingMap);
    }
}
