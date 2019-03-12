package com.silionie.server;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = {
                GeoWeatherApp.class,
                TestBase.ITConfiguration.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class TestBase {

    @LocalServerPort
    protected Integer apiPort;

    @Autowired
    protected RestTemplate restTemplate;

    @Configuration
    public static class ITConfiguration{

        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }
    }

    protected String getUri(){
        return "http://localhost:" + apiPort;
    }
}
