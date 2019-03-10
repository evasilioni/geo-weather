package com.silionie.server.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<LoginUser, Long> {

    LoginUser findByUsername(String username);

    @Query("select u from LoginUser u where u.username = :username and u.password = :password")
    LoginUser findByUsernameAndPassword(@Param("username") String username, @Param("password") String password );
}