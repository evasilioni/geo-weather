package com.silionie.server.country;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryControllerTest {
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
    public void getCountryIsoAlphaCodeSuccessfully() throws Exception {
        LoginUser user = new LoginUser();
        user.setUsername("ferryscanner");

        SecurityUser jwtUser = new SecurityUser.SecurityUserBuilder(user)
                .build();

        when(tokenProvider.getUserNameFromToken(any())).thenReturn(user.getUsername());
        when(securityUserDetailsService.loadUserByUsername(eq(user.getUsername()))).thenReturn(jwtUser);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/country/isoAlphaCode?countryCode=DE")
                .header("Authorization", "Bearer nsodunsodiuv");
        mvc.perform(builder)
                .andExpect(status().is2xxSuccessful());
    }

}
