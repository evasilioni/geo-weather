package com.silionie.server.login;

import com.silionie.server.security.SecurityUserDetailsService;
import com.silionie.server.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider jwtTokenProvider;

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    private LoginRepository loginRepository;

    public LoginUser findUser(String username){
        return loginRepository.findByUsername(username);
    }

    public String authenticate(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // Reload password post-security so we can generate the token
            final UserDetails userDetails = securityUserDetailsService.loadUserByUsername(username);
            final String token = jwtTokenProvider.createToken(userDetails.getUsername(), new ArrayList<>());

            return token;
        }catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }
}
