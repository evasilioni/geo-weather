package com.silionie.server.security;

import com.silionie.server.login.LoginUser;
import com.silionie.server.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("securityUserDetailsService")
public class SecurityUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private LoginRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        LoginUser loginUser = userRepository.findByUsername(username);

        if (loginUser == null) {
            throw new UsernameNotFoundException(String.format("No loginUser found with username '%s'.", username));
        } else {
            return new SecurityUser.SecurityUserBuilder(loginUser)
                    .build();
        }
    }
}
