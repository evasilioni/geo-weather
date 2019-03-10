package com.silionie.server.security;

public class Constants {
    public static final String SECRET_KEY = "secret";
    public static final long EXPIRATION_TIME_MILLIS = 1800000; // 30 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "authorization";
    public static final String LOGIN = "/login";
}