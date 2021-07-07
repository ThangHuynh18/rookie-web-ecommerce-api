package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Category;

public class CategoryDTO {
    private long category_id;
    private String categoryName;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.setCategory_id(category.getCategory_id());
        this.setCategoryName(category.getCategoryName());
    }

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
