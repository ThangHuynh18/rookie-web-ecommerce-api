package com.rookie.webwatch.controller;

import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<Category> getAllEmployee(){
        List<Category> categories = categoryService.retrieveCategories();
        return categories;
    }

    @GetMapping("/{category_id}")
    public Optional<Category> findCategory(@PathVariable("category_id") Long categoryId) throws ResourceNotFoundException {
        Optional<Category> category = Optional.ofNullable(categoryService.getCategory(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category not found for this id: " + categoryId)));

        return categoryService.getCategory(categoryId);
    }

    //save employee
    @PostMapping("/category")
    public Category createCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }
    //
//    //update
    @PutMapping("/category/{category_id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "category_id") Long categoryId,
                                                   @RequestBody Category categoryDetail) throws ResourceNotFoundException{
        Category category = categoryService.getCategory(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found for this id: " +categoryId));

        category.setCategoryName(categoryDetail.getCategoryName());
     //   category.setProducts(categoryDetail.getProducts());


        return ResponseEntity.ok(categoryService.updateCategory(category));
    }
    //
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
