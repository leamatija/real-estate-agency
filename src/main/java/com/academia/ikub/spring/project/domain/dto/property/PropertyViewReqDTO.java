package com.academia.ikub.spring.project.domain.dto.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyViewReqDTO {
    private Integer id;
    private Date date;
    private Integer userId;
    private String comment;


}
