package com.silionie.server.weatherObservation;

public class Station {
//     private double lng;
//     private String weatherConditionCode;
//     private String observation;
//     private String ICAO;
//     private String clouds;
//     private String dewPoint;
//     private String cloudsCode;
//     private String datetime;
     private String temperature;
//     private String humidity;
     private String stationName;
//     private String weatherCondition;
//     private Integer windDirection;
//     private String windSpeed;
//     private double lat;


    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
