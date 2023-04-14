package com.academia.ikub.spring.project.repository;

import com.academia.ikub.spring.project.domain.entity.PropertyViewRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyViewRequestRepository extends JpaRepository<PropertyViewRequest,Integer> {
}
