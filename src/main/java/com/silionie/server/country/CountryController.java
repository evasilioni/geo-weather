package com.silionie.server.country;

import com.silionie.server.security.TokenProvider;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.silionie.server.security.Constants.HEADER_STRING;

@RestController
@RequestMapping("country")
public class CountryController {

    @Autowired
    private TokenProvider jwtTokenProvider;

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/isoAlphaCode",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getCountryIsoAlphaCode(@RequestParam("countryCode") String countryCode, HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING).substring(7);
        String username = jwtTokenProvider.getUserNameFromToken(token);

        String isoAlpha3 = countryService.getIsoAlpha3(username, countryCode);
        if(!isoAlpha3.isEmpty()){
           return ResponseEntity.status(HttpStatus.OK).body(isoAlpha3);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
