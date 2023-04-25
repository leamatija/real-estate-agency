package com.academia.ikub.spring.project.domain.dto.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyUpdateDTO {
    private String name;
    private String status;
    private String imgURL;
    private Double price;
}
