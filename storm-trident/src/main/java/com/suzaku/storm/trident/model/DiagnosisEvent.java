package com.suzaku.storm.trident.model;

import java.io.Serializable;

/**
 * Description: DiagnosisEvent
 * Author: 文超
 * Create: 2015-11-15 16:37
 */

public class DiagnosisEvent implements Serializable {
    private static final long serialVersionUID = 7211157692567701006L;

    public double latitude;
    public double longitude;
    public long time;
    public String diagnosisCode;

    public DiagnosisEvent(double latitude, double longitude, long time, String diagnosisCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.diagnosisCode = diagnosisCode;
    }
}
