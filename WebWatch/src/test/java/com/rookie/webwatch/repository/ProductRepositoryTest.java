package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Product;

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
public class ProductRepositoryTest {
    @Autowired
    Productrepository productrepository;

    @Test
    public void createProduct(){
        Product product = new Product();
        Category category = new Category(1,"category 1");
        product.setProduct_id(20L);
        product.setProductName("product 20");
        product.setProductPrice(2020);
        product.setProductQty(20);
        product.setProductDescription("description 20");
        product.setCategory(category);

        Assert.assertNotNull(productrepository.save(product));
    }

    @Test
    public void GivenGetAllProductShouldReturnListOfAllProducts(){
        Category category = new Category(1,"category 1");
        Product product1 = new Product("product name", 202020,"product description", 20, category);
        Product product2 = new Product("product name 1", 202021,"product description 1", 21, category);

        productrepository.save(product1);
        productrepository.save(product2);
        List<Product> products = productrepository.findAll();
        assertEquals("product name", products.get(17).getProductName());
    }

    @Test
    public void givenIdThenShouldReturnProductOfThatId() {
        Category category = new Category(1,"category 1");
        Product product1 = new Product("product name 2", 202022,"product description 2", 22, category);
        Product product = productrepository.save(product1);
        Optional<Product> optional =  productrepository.findById(product.getProduct_id());
        assertEquals(product.getProduct_id(), optional.get().getProduct_id());
        assertEquals(product.getProductName(), optional.get().getProductName());
    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheProduct() {
        Category category = new Category(1,"category 1");
        Product product1 = new Product("product name 3", 202023,"product description 3", 23, category);
        productrepository.save(product1);
        productrepository.deleteById(product1.getProduct_id());
        Optional optional = productrepository.findById(product1.getProduct_id());
        assertEquals(Optional.empty(), optional);
    }
}
