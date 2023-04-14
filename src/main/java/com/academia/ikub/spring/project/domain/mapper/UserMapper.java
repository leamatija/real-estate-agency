package com.academia.ikub.spring.project.domain.mapper;

import com.academia.ikub.spring.project.domain.dto.user.UserDTO;
import com.academia.ikub.spring.project.domain.dto.user.UserUpdateDTO;
import com.academia.ikub.spring.project.domain.entity.User;
import com.academia.ikub.spring.project.domain.entity.UserRole;

public class UserMapper {
    public static UserDTO toDto(User u){
        return UserDTO.builder()
                .id(u.getId())
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .build();
    }

    public static User toEntity(UserDTO u){
        return User.builder()
                .id(u.getId())
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .build();
    }

    public static UserUpdateDTO toUpdateDto (User u){
        return UserUpdateDTO.builder()
                .id(u.getId())
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .build();
    }

    public static User toUpdateEntity (User u, UserUpdateDTO updateDTO){
        u.setFirstname(updateDTO.getFirstname());
        u.setLastname(updateDTO.getLastname());
        u.setEmail(updateDTO.getEmail());
        u.setPhoneNumber(updateDTO.getPhoneNumber());
        return u;
    }

}
