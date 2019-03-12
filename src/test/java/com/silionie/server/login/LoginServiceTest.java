package com.silionie.server.login;

import com.silionie.server.TestBase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginServiceTest extends TestBase {

    @Autowired
    private LoginService loginService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAuthenticationSuceess(){
        String token = loginService.authenticate("ferryscanner", "P@ssword");
        assertThat(token, is(not(isEmptyString())));
    }

    @Test
    public void testAuthenticationFailure(){
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(containsString("Bad credentials!"));

        loginService.authenticate("ferryscanner", "1qaz@WSX");

    }
}
