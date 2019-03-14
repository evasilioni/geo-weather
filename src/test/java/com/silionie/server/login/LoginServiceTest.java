package com.silionie.server.login;

import com.silionie.server.TestBase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

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
