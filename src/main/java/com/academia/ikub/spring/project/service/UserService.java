package com.academia.ikub.spring.project.service;

import com.academia.ikub.spring.project.domain.dto.user.UserDTO;
import com.academia.ikub.spring.project.domain.dto.user.UserUpdateDTO;
import com.academia.ikub.spring.project.domain.entity.User;

import java.util.List;

public interface UserService {
    User findUserById (Integer id);
    List<UserDTO> findUsers();
    Void deleteUser(Integer id);
    UserUpdateDTO updateUser(Integer id, UserUpdateDTO userDto);
    UserDTO registerUser (UserDTO userDTO, String userRole);
















}
