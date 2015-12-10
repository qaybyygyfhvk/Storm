package com.suzaku.storm.trident.spout;

import com.suzaku.storm.trident.model.DiagnosisEvent;
import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout;
import storm.trident.topology.TransactionAttempt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: DiagnosisEventEmitter
 * Author: 文超
 * Create: 2015-11-15 16:30
 */

public class DiagnosisEventEmitter implements ITridentSpout.Emitter<Long>, Serializable {

    private static final long serialVersionUID = -457966542213875842L;
    AtomicInteger successfulTransactions = new AtomicInteger(0);

    public void emitBatch(TransactionAttempt transactionAttempt, Long aLong, TridentCollector tridentCollector) {
        for (int i = 0; i < 10000; i++) {
            List<Object> events = new ArrayList<Object>();
            double latitude = new Double(-30 + (int) (Math.random() * 75));
            double longitude = new Double(-120 + (int) (Math.random() * 70));
            long time = System.currentTimeMillis();
            String diagnosis = new Integer(320 + (int) (Math.random() * 7)).toString();
            DiagnosisEvent event = new DiagnosisEvent(latitude, longitude, time, diagnosis);
            events.add(event);
            tridentCollector.emit(events);
        }
    }

    public void success(TransactionAttempt transactionAttempt) {
        successfulTransactions.incrementAndGet();
    }

    public void close() {

    }
}
