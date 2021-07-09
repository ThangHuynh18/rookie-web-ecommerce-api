package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.CategoryDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<CategoryDTO> getAllCategory(){
        List<CategoryDTO> categoryDTOS = categoryService.retrieveCategories();

        return categoryDTOS;

    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> getCate(@PathVariable("category_id") Long id) throws ResourceNotFoundException {
        CategoryDTO categoryDTO = categoryService.getCate(id);

        return ResponseEntity.ok(categoryDTO);
    }

    //save employee

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createCate(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO dto = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.ok(dto);
    }


    //update
    @PutMapping("/category/{category_id}")
    public ResponseEntity<CategoryDTO> updateCate(@PathVariable(value = "category_id") Long categoryId, @RequestBody CategoryDTO categoryDTO) throws ResourceNotFoundException {
        CategoryDTO updateCate = categoryService.updateCategory(categoryId, categoryDTO);

        return new ResponseEntity<>(updateCate, HttpStatus.OK);
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
}
