package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private long category_id;

    @NotBlank
    private String categoryName;
    private long parent_id;


    public CategoryDTO convertToDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory_id(category.getCategory_id());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setParent_id(category.getParent().getCategory_id());

        return categoryDTO;
    }

    public CategoryDTO(long category_id, String categoryName) {
        this.category_id = category_id;
        this.categoryName = categoryName;
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

}
