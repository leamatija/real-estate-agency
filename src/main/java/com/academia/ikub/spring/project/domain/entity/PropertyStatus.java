package com.academia.ikub.spring.project.domain.entity;

import com.academia.ikub.spring.project.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;
@AllArgsConstructor
public enum PropertyStatus {

    FOR_SALE("ON_SALE"),
    SOLD("SOLD"),
    FOR_RENT("FOR_RENT");

    private String value;

    public static PropertyStatus fromValue(String value){
        return Arrays.asList(PropertyStatus.values())
                .stream().filter(r-> r.value.equals(value))
                .findFirst()
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Status not found"));
    }

    public String getValue(){
        return value;
    }


}
