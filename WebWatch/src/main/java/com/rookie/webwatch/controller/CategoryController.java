package com.rookie.webwatch.controller;

import com.rookie.webwatch.convert.CategoryConvert;
import com.rookie.webwatch.dto.CategoryDTO;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public List<CategoryDTO> getAllCategory(){
//        List<Category> categories = categoryService.retrieveCategories();
//        return categories;
        return categoryService.retrieveCategories().stream().map(CategoryDTO::new).collect(Collectors.toList());

    }

    @GetMapping("/{category_id}")
    public CategoryDTO getCate(@PathVariable("category_id") Long id) throws ResourceNotFoundException {
        Category category = categoryService.getCategory(id).orElseThrow(() -> new ResourceNotFoundException("category not found for this id: " +id));

        return convertToDto(categoryService.getCategory(id));
    }

    //save employee

    @PostMapping("/category")
    public CategoryDTO createCate(@RequestBody CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category cateCreate = categoryService.saveCategory(category);
        return convertToDto(Optional.ofNullable(cateCreate));
    }


    //update
    @PutMapping("/category/{category_id}")
    public ResponseEntity<CategoryDTO> updateCate(@PathVariable(value = "category_id") Long categoryId, @RequestBody CategoryDTO categoryDTO) throws ResourceNotFoundException {
        Category category = categoryService.getCategory(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found for this id: " +categoryId));
        category.setCategoryName(categoryDTO.getCategoryName());

       return ResponseEntity.ok(convertToDtoForUpdate(categoryService.updateCategory(category)));
    }

//    //delete
    @DeleteMapping("/category/{category_id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value = "category_id") Long categoryId)
            throws ResourceNotFoundException {
        categoryService.deleteCategory(categoryId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }


    private CategoryDTO convertToDto(Optional<Category> category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        categoryDTO.setCategory_id(category.get().getCategory_id());
        categoryDTO.setCategoryName(category.get().getCategoryName());

        return categoryDTO;
    }


    private CategoryDTO convertToDtoForUpdate(Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        categoryDTO.setCategory_id(category.getCategory_id());
        categoryDTO.setCategoryName(category.getCategoryName());

        return categoryDTO;
    }


    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setCategoryName(categoryDTO.getCategoryName());

        return category;
    }
}
