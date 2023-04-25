package com.academia.ikub.spring.project.service;

import com.academia.ikub.spring.project.BaseTest;
import com.academia.ikub.spring.project.domain.dto.user.UserDTO;
import com.academia.ikub.spring.project.domain.entity.User;
import com.academia.ikub.spring.project.domain.exception.ResourceNotFoundException;
import com.academia.ikub.spring.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService toTest;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;

    @Test
    public void test_findUserById_ok(){
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
        User out = toTest.findUserById(1);
        assertNotNull(out);
    }

    @Test
    public void test_findUserById_ko(){
        Mockito.doThrow(new ResourceNotFoundException("User not found"))
                .when(userRepository).findById(Mockito.anyInt());
        Throwable throwable=assertThrows(Throwable.class,()-> toTest.findUserById(1));
        assertEquals(ResourceNotFoundException.class,throwable.getClass());
    }

    @Test
    public void test_registerUser_ok(){
        Mockito.doReturn("anyPass").when(encoder).encode(Mockito.anyString());
        Mockito.doReturn(new User ()).when(userRepository).save(Mockito.any());
        UserDTO out = toTest.registerUser(new UserDTO(),"BUYER");
        assertNotNull(out);
    }

    @Test
    public void test_findUsers_ok(){
        List<User> users = new ArrayList<>();
        Mockito.doReturn(users).when(userRepository).findAll();
        List<UserDTO> out =toTest.findUsers();
        assertNotNull(out);
    }
    @Test
    public void test_deleteUser_ok(){
        Mockito.doNothing().when(userRepository).deleteById(Mockito.anyInt());
        toTest.deleteUser(1);
    }




}
