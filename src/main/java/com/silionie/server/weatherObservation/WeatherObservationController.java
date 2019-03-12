package com.silionie.server.weatherObservation;

import com.silionie.server.security.TokenProvider;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.silionie.server.security.Constants.HEADER_STRING;

@RestController
public class WeatherObservationController {

    @Autowired
    private TokenProvider jwtTokenProvider;

    @Autowired
    private WeatherObservationService weatherObservationService;

    @RequestMapping(value = "/weatherObservation",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WeatherobservationResponse getWeatherObservation(HttpServletRequest request, @RequestParam("isoAlphaCountyCode") String isoAlphaCountyCode) {
        String token = request.getHeader(HEADER_STRING).substring(7);
        String username = jwtTokenProvider.getUserNameFromToken(token);

        List<Station> stations = weatherObservationService
                .getWeatherObservationCountry(username, isoAlphaCountyCode);
        if (stations.size() <= 0) {
            return new WeatherobservationResponse.WeatherobservationBuilder()
                    .setStations(stations)
                    .setStatus(HttpStatus.NOT_FOUND)
                    .setMessage("No weather stations found for country with isoAlphaCountyCode " + isoAlphaCountyCode)
                    .build();
        }
        return new WeatherobservationResponse.WeatherobservationBuilder()
                .setStations(stations)
                .setStatus(HttpStatus.OK)
                .build();
    }

}
