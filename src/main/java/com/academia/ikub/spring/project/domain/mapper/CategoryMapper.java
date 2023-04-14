package com.academia.ikub.spring.project.domain.mapper;

import com.academia.ikub.spring.project.domain.dto.property.CategoryDTO;
import com.academia.ikub.spring.project.domain.entity.Category;

public class CategoryMapper {
    public static Category toEntity (CategoryDTO c){
        return Category.builder()
                .id(c.getId())
                .name(c.getName())
                .build();
    }
    public static CategoryDTO toDto (Category c){
        return CategoryDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .build();
    }

}
