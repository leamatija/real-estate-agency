package com.academia.ikub.spring.project.domain.dto.property;

import com.academia.ikub.spring.project.domain.entity.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDTO {
    private Integer id;
    private String name;
    private String address;
    private String location;
    private String description;
    private String status;
    private String imgURL;
    private CategoryDTO categoryDTO;
    private Double price;
}
