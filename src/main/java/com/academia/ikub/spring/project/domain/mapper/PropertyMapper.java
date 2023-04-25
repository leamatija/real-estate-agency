package com.academia.ikub.spring.project.domain.mapper;

import com.academia.ikub.spring.project.domain.dto.property.CategoryDTO;
import com.academia.ikub.spring.project.domain.dto.property.PropertyDTO;
import com.academia.ikub.spring.project.domain.dto.property.PropertyUpdateDTO;
import com.academia.ikub.spring.project.domain.dto.property.SoldPropertiesDTO;
import com.academia.ikub.spring.project.domain.entity.Category;
import com.academia.ikub.spring.project.domain.entity.Property;
import com.academia.ikub.spring.project.domain.entity.PropertyStatus;
import com.academia.ikub.spring.project.domain.entity.SoldProperties;

public class PropertyMapper {
    public static PropertyDTO toDto (Property p){
        return PropertyDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .address(p.getAddress())
                .description(p.getDescription())
                .location(p.getLocation())
                .imgURL(p.getImgURL())
                .price(p.getPrice())
                .createdAt(p.getCreatedAt())
                .status(p.getStatus().getValue())
                .categoryDTO(p.getCategory()!=null?dto(p.getCategory()):null)
                .build();
    }

    public static Property toEntity (PropertyDTO p){
        return Property.builder()
                .id(p.getId())
                .name(p.getName())
                .address(p.getAddress())
                .description(p.getDescription())
                .location(p.getLocation())
                .createdAt(p.getCreatedAt())
                .imgURL(p.getImgURL())
                .status(PropertyStatus.fromValue(p.getStatus()))
                .price(p.getPrice())
                .build();
    }

    public static PropertyUpdateDTO toUpdateDto (Property p){
        return PropertyUpdateDTO.builder()
                .name(p.getName())
                .imgURL(p.getImgURL())
                .price(p.getPrice())
                .status(p.getStatus().getValue())
                .build();
    }
    public static Property toUpdateEntity (PropertyUpdateDTO updateDTO, Property p){
            p.setName(updateDTO.getName());
            p.setImgURL(updateDTO.getImgURL());
            p.setPrice(updateDTO.getPrice());
            p.setStatus(PropertyStatus.fromValue(updateDTO.getStatus()));
        return p;
    }
    public static CategoryDTO dto (Category c){
        return CategoryDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .build();
    }

    public static Category toEntity (CategoryDTO c){
        return Category.builder()
                .id(c.getId())
                .name(c.getName())
                .build();
    }

    public static SoldPropertiesDTO toDto (SoldProperties soldProperties){
        return SoldPropertiesDTO.builder()
                .id(soldProperties.getId())
                .commission_rate(soldProperties.getCommissionRate())
                .customerName(soldProperties.getRequest().getUser().getFirstname())
                .customerPhoneNr(soldProperties.getRequest().getUser().getPhoneNumber())
                .email(soldProperties.getRequest().getUser().getEmail())
                .address(soldProperties.getRequest().getProperty().getAddress())
                .price(soldProperties.getRequest().getProperty().getPrice())
                .build();
    }

}
