package com.silionie.server.weatherObservation;

import com.silionie.server.login.LoginService;
import com.silionie.server.login.LoginUser;
import com.silionie.server.security.SecurityUser;
import com.silionie.server.security.SecurityUserDetailsService;
import com.silionie.server.security.TokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherObservationControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private TokenProvider tokenProvider;
    @MockBean
    private SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    private LoginService loginService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void getWeatherObservationSuccessfully() throws Exception {
        LoginUser user = new LoginUser();
        user.setUsername("ferryscanner");

        SecurityUser jwtUser = new SecurityUser.SecurityUserBuilder(user)
                .build();

        when(tokenProvider.getUserNameFromToken(any())).thenReturn(user.getUsername());
        when(securityUserDetailsService.loadUserByUsername(eq(user.getUsername()))).thenReturn(jwtUser);

        mvc.perform(get("/weatherObservation?isoAlphaCountyCode=DEU").header("Authorization", "Bearer nsodunsodiuv"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void numberOfRequestsExceeded() throws Exception {
        LoginUser user = loginService.findUser("ferryscanner");
        user.setNumberOfRequests(user.getNumberOfRequests()+20001);

        SecurityUser jwtUser = new SecurityUser.SecurityUserBuilder(user)
                .build();

        when(tokenProvider.getUserNameFromToken(any())).thenReturn(user.getUsername());
        when(securityUserDetailsService.loadUserByUsername(eq(user.getUsername()))).thenReturn(jwtUser);

        mvc.perform(get("/weatherObservation?isoAlphaCountyCode=DEU").header("Authorization", "Bearer nsodunsodiuv"))
                .andExpect(jsonPath("$.status").value("TOO_MANY_REQUESTS"));
    }

}
