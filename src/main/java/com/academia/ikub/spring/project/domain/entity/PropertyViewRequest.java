package com.academia.ikub.spring.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "view_request")
public class PropertyViewRequest {
    @Id
    @GeneratedValue
    private Integer id;
    private Date date;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id",referencedColumnName = "id")
    private Property property;
}
