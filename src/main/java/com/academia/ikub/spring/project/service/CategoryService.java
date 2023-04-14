package com.academia.ikub.spring.project.service;

import com.academia.ikub.spring.project.domain.dto.property.CategoryDTO;

public interface CategoryService {
    CategoryDTO addCategory (CategoryDTO categoryDTO);
    Void deleteCategory (Integer id);
}
