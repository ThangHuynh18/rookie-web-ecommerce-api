package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.CategoryDTO;

import com.rookie.webwatch.entity.Category;

import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> retrieveCategories() {
        List<Category> categories = categoryRepository.findAll();
        return new CategoryDTO().toListDto(categories);
    }

    @Override
    public CategoryDTO getCate(Long cateId) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(cateId).orElseThrow(() -> new ResourceNotFoundException("category not found for this id: "+cateId));
        return new CategoryDTO().convertToDto(category);
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = new CategoryDTO().convertToEti(categoryDTO);
        return new CategoryDTO().convertToDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long categoryId) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found for this id: " + categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) throws ResourceNotFoundException {
        Category cateExist = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("category not found for this id: "+categoryId));

        cateExist.setCategoryName(categoryDTO.getCategoryName());

        Category category = new Category();
        category = categoryRepository.save(cateExist);
        return new CategoryDTO().convertToDto(category);
    }

}
