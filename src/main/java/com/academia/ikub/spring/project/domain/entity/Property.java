package com.academia.ikub.spring.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "property_findPropertyByCategory"
                , query = "select p from Property p where p.category.id=: categoryId")
}
)

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String address;
    private String location;
    private String description;
    @Enumerated(EnumType.STRING)
    private PropertyStatus status;
    private String imgURL;
    private Double price;
    @CreatedDate
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<PropertyViewRequest> requests;
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;
}
