package com.silionie.server.weatherObservation;

import java.util.ArrayList;
import java.util.List;

public class Observation {
    private List<Station> weatherObservations = new ArrayList<>();

    public List<Station> getWeatherObservations() {
        return weatherObservations;
    }

    public void setWeatherObservations(List<Station> weatherObservations) {
        this.weatherObservations = weatherObservations;
    }
}
