package com.silionie.server.weatherObservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silionie.server.TestBase;
import com.silionie.server.country.Country;
import com.silionie.server.country.Geonames;
import com.silionie.server.login.AuthenticationResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WeatherObservationControllerTest extends TestBase {

    @Autowired
    private WeatherObservationService weatherObservationService;

    @Test
    public void testWeatherObservationStations() throws IOException {
//        String request = "{ \n" +
//                "\t\"username\":\"ferryscanner\",\n" +
//                "\t\"password\":\"P@ssword\"\n" +
//                "}";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
//
//        ResponseEntity<AuthenticationResponse> exchange = restTemplate.exchange(
//                getUri() + "/login",
//                HttpMethod.POST,
//                httpEntity,
//                AuthenticationResponse.class);
//
//        HttpHeaders headers2 = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(exchange.getBody().getToken());
//        HttpEntity<String> httpEntity2 = new HttpEntity<>("", headers2);
//
//        ResponseEntity<WeatherobservationResponse> exchange2 = restTemplate.exchange(
//                getUri() + "/weatherObservation",
//                HttpMethod.GET,
//                httpEntity,
//                WeatherobservationResponse.class);

//        assertThat(10, equalTo(stations.size()));
    }

}
