package com.silionie.server.country;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {

    @Autowired
    private RestTemplate restTemplate;

    public String getIsoAlpha3(String username, String countryCode) {
        String countryUri = String.format("http://api.geonames.org/countryInfoJSON?country=%s&username=%s", countryCode, username);
        ResponseEntity<Geonames> countryResponse = restTemplate
                .getForEntity(countryUri, Geonames.class);

        if(countryResponse.getStatusCode().compareTo(HttpStatus.OK)==0 &&
                countryResponse.getBody() != null &&
                countryResponse.getBody().getGeonames().size()>0){

            Country country = countryResponse.getBody().getGeonames().get(0);
            if(country.getIsoAlpha3() != null){
                return  country.getIsoAlpha3();
            }
        }
        return "";
    }

    public Country getCountryByIsoAlphaCode(String username, String isoAlphaCode) {
        String countryUri = String.format("http://api.geonames.org/countryInfoJSON?username=%s", username);
        ResponseEntity<Geonames> countryResponse = restTemplate
                .getForEntity(countryUri, Geonames.class);

        if(countryResponse.getStatusCode().compareTo(HttpStatus.OK)==0 &&
                countryResponse.getBody() != null &&
                countryResponse.getBody().getGeonames().size()>0){

            return countryResponse.getBody().getGeonames()
                    .stream()
                    .filter(country -> country.getIsoAlpha3().equals(isoAlphaCode))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
