package com.rookie.webwatch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.webwatch.dto.ProductDTO;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Product;

import com.rookie.webwatch.exception.BadRequestException;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.ProductService;
import org.junit.Test;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    private final List<ProductDTO> productDTOS = new ArrayList<>();

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void getAllTest() throws Exception {
        when(productService.retrieveProducts()).thenReturn(this.productDTOS);
        this.mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())                                             // Mong muốn Server trả về status 200
                .andExpect(jsonPath("$.size()", is(productDTOS.size())));

    }

    @Test
    public void givenProductId_whenGetUser() throws Exception {
        ProductDTO productDTO = new ProductDTO("test6", 342424, "test description6", 50, 1L);

        given(productService.getProduct(productDTO.getProduct_id())).willReturn(Optional.of(productDTO));

        mockMvc.perform(get("/api/products/34")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$", hasSize(1)))
        //.andExpect(jsonPath("userName", is(userDTO.getUserName())));
    }

    @Test
    public void createProduct(){
        ProductDTO productDTO = new ProductDTO("test6", 342424, "test description6", 50, 1L);

        String exampleJson = "{\"id\":\"34\",\"productName\":\"test6\",\"productPrice\":\"342424\",\"productDescription\":\"test description6\", ,\"productQty\":\"50\",\"category_id\":\"1\"}";

        try {
            when(productService.saveProduct(productDTO)).thenReturn(productDTO);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } catch (BadRequestException e) {
            e.printStackTrace();
        }

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/products/product")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void updateProduct() throws Exception {
        String uri = "/api/products/product/20";
        Product product = new Product();
        Category category = new Category(1,"category 1");
        product.setProduct_id(20L);
        product.setProductName("product 20");
        product.setProductPrice(2020);
        product.setProductQty(20);
        product.setProductDescription("description 20");
        product.setCategory(category);

        String inputJson = mapToJson(product);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }


    @Test
    public void deleteProduct() throws Exception {
        String uri = "/api/products/product/20";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
