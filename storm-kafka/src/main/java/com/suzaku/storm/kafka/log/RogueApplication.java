package com.suzaku.storm.kafka.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: RogueApplication
 * Author: 文超
 * Create: 2015-11-25 23:24
 */

public class RogueApplication {

    private static Logger logger = LoggerFactory.getLogger(RogueApplication.class);

    public static void main(String[] args) throws InterruptedException {
        int slowCount = 6;
        int fastCount = 15;

        for (int i = 0; i < slowCount; i++) {
            logger.warn("This is a warning (slow state)");
            Thread.sleep(5000);
        }

        for (int i = 0; i < fastCount; i++) {
            logger.warn("This is a warning (fast state)");
            Thread.sleep(1000);
        }

        for (int i = 0; i < slowCount; i++) {
            logger.warn("This is a warning (slow state)");
            Thread.sleep(5000);
        }
    }
}
