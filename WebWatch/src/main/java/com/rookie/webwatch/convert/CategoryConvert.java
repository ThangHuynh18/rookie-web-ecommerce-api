package com.rookie.webwatch.convert;

import com.rookie.webwatch.dto.CategoryDTO;
import com.rookie.webwatch.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConvert {
    public CategoryDTO etyToDto(Category category){
        ModelMapper mapper = new ModelMapper();
        CategoryDTO map = mapper.map(category, CategoryDTO.class);
        return map;
    }

    public List<CategoryDTO> etyToDto(List<Category> categories){
        return categories.stream().map(x -> etyToDto(x)).collect(Collectors.toList());
    }

    public Category dtoToEty(CategoryDTO dto){
        ModelMapper mapper = new ModelMapper();
        Category map = mapper.map(dto, Category.class);
        return map;
    }
}
