package com.academia.ikub.spring.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sold_properties")
public class SoldProperties {
    @Id
    @GeneratedValue
    private Integer id;
    private Double commissionRate;

    @OneToOne
    @JoinColumn(name = "request_id",referencedColumnName = "id")
    private PropertyViewRequest request;

}
