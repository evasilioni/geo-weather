package com.silionie.server.login;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private String token;
    private HttpStatus status;
    private String userMessage;
    private String internalMessage;


    public AuthenticationResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getUserMessage() {
        return userMessage;
    }
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
    public String getInternalMessage() {
        return internalMessage;
    }

    public void setInternalMessage(String internalMessage) {
        this.internalMessage = internalMessage;
    }

    private AuthenticationResponse(AuthenticationResponseBuilder builder) {
        this.token = builder.token;
        this.status = builder.status;
        this.userMessage=builder.userMessage;
        this.internalMessage = builder.internalMessage;
    }

    //Builder Class
    public static class AuthenticationResponseBuilder{
        private String token;
        private HttpStatus status;
        private String userMessage;
        private String internalMessage;

        public AuthenticationResponseBuilder setToken(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponseBuilder setStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public AuthenticationResponseBuilder setUserMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public AuthenticationResponseBuilder setInternalMessage(String internalMessage) {
            this.internalMessage = internalMessage;
            return this;
        }

        public AuthenticationResponse build(){
            return new AuthenticationResponse(this);
        }
    }
}

