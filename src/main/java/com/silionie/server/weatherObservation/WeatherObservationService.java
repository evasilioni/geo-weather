package com.silionie.server.weatherObservation;
import com.silionie.server.country.Country;
import com.silionie.server.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherObservationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CountryService countryService;

    public List<Station> getWeatherObservationCountry(String username, String isoAlphaCountyCode) {
        Country countryByIsoAlphaCode = countryService.getCountryByIsoAlphaCode(username, isoAlphaCountyCode);
        if(countryByIsoAlphaCode == null) return new ArrayList<>();

        ResponseEntity<Observation> observationResponse = restTemplate
                .getForEntity(Utilities.getWeatherUri(username, countryByIsoAlphaCode), Observation.class);

        if(observationResponse.getStatusCode().compareTo(HttpStatus.OK)==0 &&
                observationResponse.getBody() != null &&
                observationResponse.getBody().getWeatherObservations().size()>0){
            return observationResponse.getBody().getWeatherObservations();
        }
        return new ArrayList<>();
    }


}
