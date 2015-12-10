package com.suzaku.storm.trident.filter;

import com.suzaku.storm.trident.model.DiagnosisEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFilter;
import storm.trident.tuple.TridentTuple;

/**
 * Description: DiseaseFilter
 * Author: 文超
 * Create: 2015-11-15 17:02
 */

public class DiseaseFilter extends BaseFilter {


    private static final long serialVersionUID = -3427145466108047267L;

    private Logger logger = LoggerFactory.getLogger(DiseaseFilter.class);

    public boolean isKeep(TridentTuple tridentTuple) {
        DiagnosisEvent event = (DiagnosisEvent) tridentTuple.getValue(0);
        String diagnosisCode = event.diagnosisCode;
        Integer code = Integer.parseInt(event.diagnosisCode);
        if(code.intValue() <= 322){
            logger.debug("Emitting disease[{}]", diagnosisCode);
            return true;
        }else {
            logger.debug("Filtering disease[{}]", diagnosisCode);
            return false;
        }
    }
}
