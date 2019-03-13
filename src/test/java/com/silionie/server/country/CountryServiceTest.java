package com.silionie.server.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silionie.server.TestBase;
import com.silionie.server.weatherObservation.Station;
import com.silionie.server.weatherObservation.WeatherObservationService;
import com.silionie.server.weatherObservation.WeatherObservationServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CountryServiceTest extends TestBase {


    @Autowired
    private CountryService countryService;

    private static ObjectMapper objectMapper;
    private static final String COUNTRY_FILE = "countryInfoDE.json";
    private static URL countryFileUrl;

    @Before
    public void init(){
        objectMapper = new ObjectMapper();
        countryFileUrl = WeatherObservationServiceTest.class.getClassLoader().getResource(COUNTRY_FILE);
    }

    @Test
    public void testIsoAlpha3Success() throws IOException {
        Geonames geonames = objectMapper.readValue(countryFileUrl, Geonames.class);
        Country country = geonames.getGeonames().stream().filter(c -> c.getCountryCode().equals("DE")).findFirst().get();

        String isoAlpha3 = countryService.getIsoAlpha3("ferryscanner", country.getCountryCode());

        assertThat("DEU", equalTo(isoAlpha3));
    }

    @Test
    public void testCountryByIsoAlphaCode() throws IOException {
        Geonames geonames = objectMapper.readValue(countryFileUrl, Geonames.class);
        Country country = geonames.getGeonames().stream().filter(c -> c.getCountryCode().equals("DE")).findFirst().get();

        Country countryByIsoAlphaCode = countryService.getCountryByIsoAlphaCode("ferryscanner", country.getIsoAlpha3());

        assertThat("DEU", equalTo(countryByIsoAlphaCode.getIsoAlpha3()));
    }

}
