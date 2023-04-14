package com.academia.ikub.spring.project.domain.mapper;

import com.academia.ikub.spring.project.domain.dto.property.PropertyViewReqDTO;
import com.academia.ikub.spring.project.domain.entity.PropertyViewRequest;

public class PropertyViewReqMapper {
    public static PropertyViewReqDTO toDto (PropertyViewRequest p){
        return PropertyViewReqDTO.builder()
                .id(p.getId())
                .comment(p.getComment())
                .date(p.getDate())
                .userId(p.getUser().getId())
                .build();
    }
    public static PropertyViewRequest toEntity (PropertyViewReqDTO p){
        return PropertyViewRequest.builder()
                .comment(p.getComment())
                .date(p.getDate())
                .build();
    }
}
