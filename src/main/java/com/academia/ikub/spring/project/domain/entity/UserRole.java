package com.academia.ikub.spring.project.domain.entity;

import com.academia.ikub.spring.project.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;


@AllArgsConstructor
public enum UserRole {

    ADMIN("ADMIN"),
    SELLER("SELLER"),
    BUYER("BUYER");

    private String value;

    public static UserRole fromValue(String userRole){
        return Arrays.asList(UserRole.values())
                .stream().filter(r-> r.value.equals(userRole))
                .findFirst()
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Role not found"));
    }

    public String getValue(){
        return value;
    }



}
