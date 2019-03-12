package com.silionie.server.weatherObservation.unitTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silionie.server.country.Country;
import com.silionie.server.country.Geonames;
import com.silionie.server.weatherObservation.Utilities;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UtilitiesTest {

    @Test
    public void testWeatherUri() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Geonames geonames = objectMapper.readValue(UtilitiesTest.class.getClassLoader().getResource("countryInfoDE.json"), Geonames.class);
        Country country = geonames.getGeonames().get(0);

        String weatherUri = Utilities.getWeatherUri("ferryscanner", country);

        String expected = "http://api.geonames.org/weatherJSON?north=55.1&south=47.3&east=15&west=5.9&username=ferryscanner";
        assertThat(expected, equalTo(weatherUri));
    }
}
