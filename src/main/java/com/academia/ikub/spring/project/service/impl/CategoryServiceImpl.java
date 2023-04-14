package com.academia.ikub.spring.project.service.impl;

import com.academia.ikub.spring.project.domain.dto.property.CategoryDTO;
import com.academia.ikub.spring.project.domain.entity.Category;
import com.academia.ikub.spring.project.domain.mapper.CategoryMapper;
import com.academia.ikub.spring.project.repository.CategoryRepository;
import com.academia.ikub.spring.project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return CategoryMapper.toDto(categoryRepository.save(CategoryMapper.toEntity(categoryDTO)));
    }

    @Override
    public Void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
        return null;
    }
}
