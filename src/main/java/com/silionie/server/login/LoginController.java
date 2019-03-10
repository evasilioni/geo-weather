package com.silionie.server.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody
    AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        LoginUser persisted = loginService.findUser(authenticationRequest.getUsername());
        if (persisted != null) {
            try{
                String token = loginService
                        .authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

                return new AuthenticationResponse.AuthenticationResponseBuilder()
                        .setStatus(HttpStatus.OK)
                        .setToken(token)
                        .build();
            } catch (AuthenticationException ex){
                return new AuthenticationResponse.AuthenticationResponseBuilder()
                        .setStatus(HttpStatus.UNAUTHORIZED)
                        .setUserMessage("User is not authorized.")
                        .setInternalMessage(ex.getMessage())
                        .build();
            }

        }

        return new AuthenticationResponse.AuthenticationResponseBuilder()
                .setStatus(HttpStatus.NOT_FOUND)
                .setUserMessage("Username:" + authenticationRequest.getUsername() + " not found.")
                .build();
    }

}
