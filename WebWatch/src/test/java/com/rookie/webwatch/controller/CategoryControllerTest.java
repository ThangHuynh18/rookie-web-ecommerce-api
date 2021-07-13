package com.rookie.webwatch.controller;

import static org.junit.Assert.assertEquals;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.webwatch.dto.CategoryDTO;

import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.service.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private final List<CategoryDTO> categories = new ArrayList<>();

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @BeforeEach
    void setUp(){
        this.categories.add(new CategoryDTO(1L,"cate1"));
        this.categories.add(new CategoryDTO(2L,"cate2"));
        this.categories.add(new CategoryDTO(3L,"cate3"));
    }

    @Test
    public void getAllTest() throws Exception {
        when(categoryService.retrieveCategories()).thenReturn(this.categories);
        this.mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())                                             // Mong muốn Server trả về status 200
                .andExpect(jsonPath("$.size()", is(categories.size())));

    }

    @Test
    public void givenCategoryId_whenGetCategory()
            throws Exception {

        CategoryDTO category = new CategoryDTO(1,"cate1");

        List<CategoryDTO> all = Arrays.asList(category);

        given(categoryService.getCate(category.getCategory_id())).willReturn(java.util.Optional.of(category));

        mockMvc.perform(get("/api/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("categoryName", is(category.getCategoryName())));
    }

    @Test
    public void createCate() throws Exception {
        CategoryDTO category = new CategoryDTO(1,"category1");

        String exampleJson = "{\"id\":\"1\",\"categoryName\":\"category1\"}";

        when(categoryService.saveCategory(category)).thenReturn(category);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/categories/category")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void updateCate() throws Exception {
        String uri = "/api/categories/category/2";
        Category category = new Category();
        category.setCategoryName("cate2");

        String inputJson = mapToJson(category);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Category is updated successsfully");
    }


    @Test
    public void deleteProduct() throws Exception {
        String uri = "/api/categories/category/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Category is deleted successsfully");
    }

}
