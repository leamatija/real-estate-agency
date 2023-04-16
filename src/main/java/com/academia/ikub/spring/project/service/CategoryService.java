package com.academia.ikub.spring.project.service;

import com.academia.ikub.spring.project.domain.dto.property.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory (CategoryDTO categoryDTO);
    Void deleteCategory (Integer id);
    List<CategoryDTO> listAllCategories();
}
