package com.silionie.server;

import com.silionie.server.login.LoginUser;
import com.silionie.server.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    LoginRepository users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        users.findAll().forEach(lu -> {
            lu.setPassword(passwordEncoder.encode(lu.getPassword()));
            users.save(lu);
        });
    }
}