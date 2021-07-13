package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void createCate(){
        Category category = new Category();
        category.setCategory_id(10L);
        category.setCategoryName("category 10");

        Assert.assertNotNull(categoryRepository.save(category));
    }

    @Test
    public void GivenGetAllCateShouldReturnListOfAllCategories(){
        Category category1 = new Category(11L, "category 11");
        Category category2 = new Category(12L, "category 12");
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        List<Category> categoryList = categoryRepository.findAll();
        assertEquals("category 11", categoryList.get(5).getCategoryName());
    }

    @Test
    public void givenIdThenShouldReturnCateOfThatId() {
        Category category1 = new Category(13L, "category 13");
        Category category = categoryRepository.save(category1);
        Optional<Category> optional =  categoryRepository.findById(category.getCategory_id());
        assertEquals(category.getCategory_id(), optional.get().getCategory_id());
        assertEquals(category.getCategoryName(), optional.get().getCategoryName());
    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheCate() {
        Category category = new Category(13L,  "category 13");
        categoryRepository.save(category);
        categoryRepository.deleteById(category.getCategory_id());
        Optional optional = categoryRepository.findById(category.getCategory_id());
        assertEquals(Optional.empty(), optional);
    }

}
