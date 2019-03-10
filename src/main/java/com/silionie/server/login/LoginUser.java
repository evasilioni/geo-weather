package com.silionie.server.login;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;


@Entity
@Table(name = "USERS")
public class LoginUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String username;
    private String password;

    public LoginUser() {
    }

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_role")
    private List<String> roles = new ArrayList<>();

    public List<String> getRoles() {
        return roles;
    }


}