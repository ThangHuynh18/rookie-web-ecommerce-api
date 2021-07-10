package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Order;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
    private long category_id;

    @NotNull
    private String categoryName;

    public CategoryDTO() {
    }

    public CategoryDTO convertToDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory_id(category.getCategory_id());
        categoryDTO.setCategoryName(category.getCategoryName());

        return categoryDTO;
    }

    public Category convertToEti(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setCategory_id(categoryDTO.getCategory_id());
        category.setCategoryName(categoryDTO.getCategoryName());

        return category;
    }


    public List<CategoryDTO> toListDto(List<Category> listEntity) {
        List<CategoryDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }

//    public CategoryDTO(Category category) {
//        this.setCategory_id(category.getCategory_id());
//        this.setCategoryName(category.getCategoryName());
//    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
