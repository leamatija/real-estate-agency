package com.academia.ikub.spring.project.domain.dto.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoldPropertiesDTO {
    private Integer id;
    private Double commission_rate;
    private String customerName;
    private String customerPhoneNr;
    private String email;
    private String address;
    private String name;
    private Double price;


}
