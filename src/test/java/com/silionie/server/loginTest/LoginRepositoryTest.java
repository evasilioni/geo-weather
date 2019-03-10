package com.silionie.server.loginTest;

import com.silionie.server.TestBase;
import com.silionie.server.login.LoginRepository;
import com.silionie.server.login.LoginUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class LoginRepositoryTest extends TestBase {

    @Autowired
    private LoginRepository loginRepository;

    @Test
    public void testFindUserByUsername(){
        LoginUser loginUser = loginRepository.findByUsername("ferryscanner");

        assertThat(loginUser.getUsername(), is(equalTo("ferryscanner")));
    }

    @Test
    public void testNotFoundUsername(){
        LoginUser loginUser = loginRepository.findByUsername("ferryscannerqq");
        assertNull(loginUser);
    }
}
