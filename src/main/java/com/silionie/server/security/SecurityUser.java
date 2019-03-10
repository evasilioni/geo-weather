package com.silionie.server.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silionie.server.login.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SecurityUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private SecurityUser(SecurityUserBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.authorities = builder.authorities;
    }

    //Builder Class
    public static class SecurityUserBuilder {

        private Long id;
        private String username;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public SecurityUserBuilder(LoginUser loginUser) {
            this.username = loginUser.getUsername();
            this.password = loginUser.getPassword();
            this.authorities = mapToGrantedAuthorities(loginUser.getRoles());
        }

        private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> roles) {
            return roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
        }

        public SecurityUser build() {
            return new SecurityUser(this);
        }
    }

}
