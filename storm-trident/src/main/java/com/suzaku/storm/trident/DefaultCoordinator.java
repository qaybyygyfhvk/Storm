package com.suzaku.storm.trident;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.spout.ITridentSpout;
import storm.trident.state.Serializer;

/**
 * Description: DefaultCoordinator
 * Author: 文超
 * Create: 2015-11-15 16:19
 */

public class DefaultCoordinator implements ITridentSpout.BatchCoordinator<Long>, Serializer {


    private static final long serialVersionUID = -8228267639059873991L;
    private static final Logger logger = LoggerFactory.getLogger(DefaultCoordinator.class);


    public Long initializeTransaction(long l, Long aLong, Long x1) {
        logger.info("Initializing Transaction [{}]", l);
        return null;
    }

    public void success(long l) {
        logger.info("Successful Transaction [{}]", l);

    }

    public boolean isReady(long l) {
        return true;
    }

    public void close() {

    }

    public byte[] serialize(Object o) {
        return new byte[0];
    }

    public Object deserialize(byte[] bytes) {
        return null;
    }
}
