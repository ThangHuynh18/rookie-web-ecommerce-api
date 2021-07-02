package com.rookie.webwatch.service;


import com.rookie.webwatch.dto.CategoryDTO;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> retrieveCategories();

    public Optional<Category> getCategory(Long categoryId);

    public Category saveCategory(Category category);

    public void deleteCategory(Long categoryId) throws ResourceNotFoundException;

    public Category updateCategory(Category category);
}
