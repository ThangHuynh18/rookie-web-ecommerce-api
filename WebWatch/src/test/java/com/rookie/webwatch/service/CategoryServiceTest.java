package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.CategoryDTO;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    private Category category1;
    private Category category2;
    private List<Category> categories = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        category1 = new Category(1L, "category1");
        category2 = new Category(2L, "category2");

        categories.add(category1);
        categories.add(category2);
    }
    @AfterEach
    public void tearDown() {
        category1 = category2 = null;
        categories = null;
    }

    @Test
    public void getCate(){
        List<CategoryDTO> categories = new ArrayList<>();
        categories.add(new CategoryDTO(1L,"cate1"));
        categories.add(new CategoryDTO(2L,"cate2"));
        categories.add(new CategoryDTO(3L,"cate3"));

         when(categoryRepository.findAll()).thenReturn(Stream.of(new Category(1,"cate1")).collect(Collectors.toList()));
        Assert.assertEquals(1,categoryService.retrieveCategories().size());
    }

    @Test
    public void addCate() throws Exception{
        CategoryDTO categoryDTO = new CategoryDTO(1,"category1");
//        when(categoryRepository.save(category)).thenReturn(category);
      //  Assert.assertEquals(true, categoryService.saveCategory(category));

        category1 = new Category(1L, "category1");


        when(categoryRepository.save(any())).thenReturn(category1);

        CategoryDTO dto = categoryService.saveCategory(categoryDTO);
        Assert.assertEquals("category1", dto.getCategoryName());
       // when(categoryService.saveCategory(Mockito.any(CategoryDTO.class))).thenReturn(category);

    }

    @Test
    public void updateCate() throws Exception{
        CategoryDTO categoryDTO = new CategoryDTO(1,"category1");
//        when(categoryRepository.save(category)).thenReturn(category);
        //  Assert.assertEquals(true, categoryService.saveCategory(category));

        category1 = new Category(1L, "category1");

        Category category = new Category(1,"cate1");
        when(categoryRepository.findById(category.getCategory_id())).thenReturn(Optional.of(category));

        when(categoryRepository.save(any())).thenReturn(category1);

        CategoryDTO dto = categoryService.saveCategory(categoryDTO);
        Assert.assertEquals("category1", dto.getCategoryName());
        // when(categoryService.saveCategory(Mockito.any(CategoryDTO.class))).thenReturn(category);

    }

    @Test
    public void deleteCate() throws ResourceNotFoundException {
        Category category = new Category(1,"cate1");
        when(categoryRepository.findById(category.getCategory_id())).thenReturn(Optional.of(category));

        categoryService.deleteCategory(category.getCategory_id());

        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    public void loadCateById() throws ResourceNotFoundException {
        // Given
        Category category = new Category();
        category.setCategory_id(1);
        category.setCategoryName("cate1");
        //categoryRepository.save(category);

        when(categoryRepository.findById(category.getCategory_id())).thenReturn(java.util.Optional.of(category));

        Assert.assertEquals("cate1",category.getCategoryName());

    }


    @Test
    public void GivenGetAllCateShouldReturnListOfAllCate(){
        categoryRepository.save(category1);
        //stubbing mock to return specific data
        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDTO> categories1 =categoryService.retrieveCategories();
        Assert.assertEquals(categories1,categories);
        verify(categoryRepository,times(1)).save(category1);
        verify(categoryRepository,times(1)).findAll();
    }


}
