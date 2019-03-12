package com.silionie.server.weatherObservation;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class WeatherobservationResponse {

    private List<Station> stations = new ArrayList<>();
    private HttpStatus status;
    private String message;

    public WeatherobservationResponse() {
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WeatherobservationResponse(WeatherobservationBuilder builder){
        this.stations = builder.stations;
        this.status = builder.status;
        this.message = builder.message;
    }

    public static class WeatherobservationBuilder{
        private List<Station> stations = new ArrayList<>();
        private HttpStatus status;
        private String message;

        public WeatherobservationBuilder setStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public WeatherobservationBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public WeatherobservationBuilder setStations(List<Station> stations) {
            this.stations = stations;
            return this;
        }

        public WeatherobservationResponse build(){
            return new WeatherobservationResponse(this);
        }
    }
}
