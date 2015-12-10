package com.suzaku.storm.trident.function;

import com.suzaku.storm.trident.model.DiagnosisEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: CityAssigment
 * Author: 文超
 * Create: 2015-11-15 17:12
 */

public class CityAssigment extends BaseFunction {


    private static final long serialVersionUID = -1267005433729970727L;
    private Logger logger = LoggerFactory.getLogger(CityAssigment.class);

    private static Map<String, double[]> CITIES = new HashMap<String, double[]>();

    { // Initialize the cities we care about.
        double[] phl = {39.875365, -75.249524};
        CITIES.put("PHL", phl);
        double[] nyc = {40.71448, -74.00598};
        CITIES.put("NYC", nyc);
        double[] sf = {-31.4250142, -62.0841809};
        CITIES.put("SF", sf);
        double[] la = {-34.05374, -118.24307};
        CITIES.put("LA", la);
    }

    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        DiagnosisEvent event = (DiagnosisEvent) tridentTuple.getValue(0);
        double leastDistance = Double.MAX_VALUE;
        String closeCity = "None";

        for (Map.Entry<String, double[]> city : CITIES.entrySet()) {
            double R = 6371; //km
            double x = (city.getValue()[0] - event.longitude) * Math.cos((city.getValue()[0] + event.longitude) / 2);
            double y = (city.getValue()[1] - event.latitude);
            double d = Math.sqrt(x * x + y * y) * R;
            if (d < leastDistance) {
                leastDistance = d;
                closeCity = city.getKey();
            }
        }

        List<Object> values = new ArrayList<Object>();
        values.add(closeCity);
        logger.debug("Closest city to lat=[{}], lng=[{}] == [{}], d=[{}]", event.latitude, event.longitude, closeCity, leastDistance);
        tridentCollector.emit(values);

    }
}
