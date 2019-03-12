package com.silionie.server.weatherObservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silionie.server.TestBase;
import com.silionie.server.country.Country;
import com.silionie.server.country.Geonames;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class WeatherObservationServiceTest extends TestBase {

    @Autowired
    private WeatherObservationService weatherObservationService;

    private static ObjectMapper objectMapper;
    private static final String COUNTRY_FILE = "countryInfoDE.json";
    private static URL countryFileUrl;

    @Before
    public void init(){
        objectMapper = new ObjectMapper();
        countryFileUrl = WeatherObservationServiceTest.class.getClassLoader().getResource(COUNTRY_FILE);
    }

    @Test
    public void testWeatherObservationStations() throws IOException {
        Geonames geonames = objectMapper.readValue(countryFileUrl, Geonames.class);
        Country country = geonames.getGeonames().stream().filter(c -> c.getCountryCode().equals("DE")).findFirst().get();

        List<Station> stations = weatherObservationService
                .getWeatherObservationCountry("ferryscanner", country.getIsoAlpha3());

        objectMapper.writeValue(new File("target/observation.json"), stations);

        assertThat(10, equalTo(stations.size()));
        assertThat(stations.stream().map(Station::getStationName).collect(Collectors.toList()), hasItems("Colmar-Houssen", "Duesseldorf"));
    }

}
