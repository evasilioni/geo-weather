package com.silionie.server.weatherObservation;

import com.silionie.server.country.Country;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Utilities {

    private static String formatCoordinate(BigDecimal coordinatePoint){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        df.setMinimumFractionDigits(0);

        return df.format(coordinatePoint).replace(',', '.');
    }

    public static String getWeatherUri(String username, Country country){
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append("http://api.geonames.org/weatherJSON?");
        uriBuilder.append("north=").append(formatCoordinate(country.getNorth()));
        uriBuilder.append("&south=").append(formatCoordinate(country.getSouth()));
        uriBuilder.append("&east=").append(formatCoordinate(country.getEast()));
        uriBuilder.append("&west=").append(formatCoordinate(country.getWest()));
        uriBuilder.append("&username=").append(username);

        return uriBuilder.toString();
    }
}
