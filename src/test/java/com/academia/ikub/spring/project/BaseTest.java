package com.academia.ikub.spring.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
        objectMapper= new ObjectMapper();
    }

    protected Authentication getAuthentication(String role){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "admin@gmail.com","admin123",authorities);
        return authentication;
    }
}
