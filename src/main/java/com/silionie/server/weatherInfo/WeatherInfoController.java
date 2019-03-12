package com.silionie.server.weatherInfo;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherInfoController {

    @RequestMapping(value = "/weatherInfo",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getWeatherInfo(@RequestParam("countryCode") String countryCode){
        return null;
    }
}
