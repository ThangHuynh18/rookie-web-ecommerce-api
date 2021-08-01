package com.rookie.webwatch.service;


import com.rookie.webwatch.dto.CategoryDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


public interface CategoryService {
    public List<CategoryDTO> retrieveCategories();

    public Optional<CategoryDTO> getCate(Long categoryId) throws ResourceNotFoundException;

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) throws ResourceNotFoundException;

    public Boolean deleteCategory(Long categoryId) throws ResourceNotFoundException;

    public CategoryDTO updateCategory(Long categoryId,CategoryDTO categoryDTO) throws ResourceNotFoundException;

    public List<CategoryDTO> getParent();

    public List<CategoryDTO> getCategoryByParent(Long categoryId) throws ResourceNotFoundException;

    public CategoryDTO createParent(CategoryDTO categoryDTO);
}
