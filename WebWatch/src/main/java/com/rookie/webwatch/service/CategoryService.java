package com.rookie.webwatch.service;


import com.rookie.webwatch.dto.CategoryDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;


public interface CategoryService {
    public List<CategoryDTO> retrieveCategories();

    public CategoryDTO getCate(Long categoryId) throws ResourceNotFoundException;

    public CategoryDTO saveCategory(CategoryDTO categoryDTO);

    public void deleteCategory(Long categoryId) throws ResourceNotFoundException;

    public CategoryDTO updateCategory(Long categoryId,CategoryDTO categoryDTO) throws ResourceNotFoundException;

}
