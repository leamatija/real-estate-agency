package com.academia.ikub.spring.project.repository;

import com.academia.ikub.spring.project.domain.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
PropertyRepository extends JpaRepository<Property,Integer> {

    @Query(name = "property_findPropertyByCategory")
    List<Property> findByCategoryId (Integer categoryId);
}
