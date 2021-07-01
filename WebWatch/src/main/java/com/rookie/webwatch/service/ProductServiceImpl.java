package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.Productrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private Productrepository productrepository;

    @Override
    public List<Product> retrieveProducts() {
        List<Product> products = productrepository.findAll();
        return products;
    }

    @Override
    public Optional<Product> getProduct(Long productId) {
        return productrepository.findById(productId);
    }

    @Override
    public Product saveProduct(Product product) {
        return productrepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) throws ResourceNotFoundException {
        Product product = productrepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found for this id: " + productId));
        this.productrepository.delete(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productrepository.save(product);
    }
}
