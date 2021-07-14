package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.Productrepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @MockBean
    Productrepository productrepository;

    private Category category = new Category(1,"category 1");
    private Product product = new Product("product name", 202020,"product description", 20, category);

    @Test
    public void getProduct(){
        when(productrepository.findAll())
                .thenReturn(Stream.of(new Product("product name", 202020,"product description", 20, category),
                                new Product("product name", 202020,"product description", 20, category))
                        .collect(Collectors.toList()));
        Assert.assertEquals(2,productService.retrieveProducts().size());
    }

    @Test
    public void addProduct(){
        when(productrepository.save(any())).thenReturn(product);

        Assert.assertEquals("product name", product.getProductName());
    }

    @Test
    public void updateProduct(){
        Product product = new Product("product name", 202020,"product description", 20, category);

        when(productrepository.findById(product.getProduct_id())).thenReturn(Optional.of(product));

        when(productrepository.save(any())).thenReturn(product);

        Assert.assertEquals("product name", product.getProductName());
    }

    @Test
    public void deleteProduct() throws ResourceNotFoundException {
        Product product = new Product("product name", 202020,"product description", 20, category);
        when(productrepository.findById(product.getProduct_id())).thenReturn(Optional.of(product));

        productService.deleteProduct(product.getProduct_id());

        verify(productrepository, times(1)).delete(product);
    }

    @Test
    public void loadProductById(){
        // Given
        Product product = new Product();
        Category category = new Category(1,"category 1");
        product.setProduct_id(20L);
        product.setProductName("product 20");
        product.setProductPrice(2020);
        product.setProductQty(20);
        product.setProductDescription("description 20");
        product.setCategory(category);

        when(productrepository.findById(product.getProduct_id())).thenReturn(java.util.Optional.of(product));

        Assert.assertEquals("product 20",product.getProductName());

    }
}
