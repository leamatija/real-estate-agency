package com.academia.ikub.spring.project.controller;

import com.academia.ikub.spring.project.BaseTest;
import com.academia.ikub.spring.project.domain.dto.auth.LoginRequest;
import com.academia.ikub.spring.project.domain.dto.user.UserDTO;
import com.academia.ikub.spring.project.domain.entity.User;
import com.academia.ikub.spring.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest extends BaseTest {
    @MockBean
    private UserService userService;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private JwtEncoder jwtEncoder;

    @Test
    public void test_login_ok() throws Exception{
        Authentication auth = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.doReturn(auth).when(authenticationManager).authenticate(any());
        User user = new User();
        user.setEmail("user@gmail.com");
        doReturn(user).when(auth).getPrincipal();
        doReturn(Arrays.asList(new SimpleGrantedAuthority("test"))).when(auth).getAuthorities();

        Jwt jwt = Mockito.mock(Jwt.class);
        doReturn(jwt).when(jwtEncoder).encode(any());
        doReturn("Bearer ").when(jwt).getTokenValue();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(new LoginRequest("user@gmail.com","password"))))
                .andExpect(status().isOk());

    }

    @Test
    public void test_registerUser() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_BUYER"));
        doReturn(new UserDTO()).when(userService).registerUser(any(), any());
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserDTO(1, "first", "last", "last@gmail.com", "pass", "8788878"))))
                .andExpect(status().isOk());
    }

    @Test
    public void test_registerUser_ko() throws Exception {
        doReturn(new UserDTO()).when(userService).registerUser(any(), any());
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserDTO())))
                .andExpect(status().is4xxClientError());
    }
}