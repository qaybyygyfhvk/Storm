package com.suzaku.storm.trident;

import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import com.suzaku.storm.trident.factory.OutbreakTrendFactory;
import com.suzaku.storm.trident.filter.DiseaseFilter;
import com.suzaku.storm.trident.function.CityAssigment;
import com.suzaku.storm.trident.function.DispatchAlert;
import com.suzaku.storm.trident.function.HourAssigment;
import com.suzaku.storm.trident.function.OutbreakDetector;
import com.suzaku.storm.trident.spout.DiagnosisEventSpout;
import storm.trident.Stream;
import storm.trident.TridentTopology;
import storm.trident.operation.builtin.Count;

/**
 * Description: OutbreakDetectionTopology
 * Author: 文超
 * Create: 2015-11-15 16:07
 */

public class OutbreakDetectionTopology {

    public static StormTopology buildTopology() {
        TridentTopology topology = new TridentTopology();
        DiagnosisEventSpout spout = new DiagnosisEventSpout();
        Stream input = topology.newStream("event", spout);
        input.each(new Fields("event"), new DiseaseFilter())
                .each(new Fields("event"), new CityAssigment(), new Fields("city"))
                .each(new Fields("event", "city"), new HourAssigment(), new Fields("hour", "cityDiseaseHour"))
                .groupBy(new Fields("cityDiseaseHour")).persistentAggregate(new OutbreakTrendFactory(), new Count(), new Fields("count"))
                .newValuesStream().each(new Fields("cityDiseaseHour", "count"), new OutbreakDetector(), new Fields("alert"))
                .each(new Fields("alert"), new DispatchAlert(), new Fields());
        return null;
    }
}
