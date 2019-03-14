package com.silionie.server.country;

import com.silionie.server.login.LoginService;
import com.silionie.server.login.LoginUser;
import com.silionie.server.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.silionie.server.security.Constants.HEADER_STRING;

@RestController
@RequestMapping("country")
public class CountryController {

    @Autowired
    private TokenProvider jwtTokenProvider;

    @Autowired
    private LoginService loginService;

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/isoAlphaCode",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getCountryIsoAlphaCode(@RequestParam("countryCode") String countryCode, HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING).substring(7);
        String username = jwtTokenProvider.getUserNameFromToken(token);

        LoginUser loginuser = loginService.findUser(username);
        loginuser.setNumberOfRequests(loginuser.getNumberOfRequests()+1);
        if(loginuser.getNumberOfRequests() > 20000){
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("User exceeded the number of requests per day");
        }

        String isoAlpha3 = countryService.getIsoAlpha3(username, countryCode);
        if(!isoAlpha3.isEmpty()){
           return ResponseEntity.status(HttpStatus.OK).body(isoAlpha3);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
