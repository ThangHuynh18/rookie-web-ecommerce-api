package com.rookie.webwatch.service;


import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> retrieveProducts();

    public Optional<Product> getProduct(Long productId);

    public Product saveProduct(Product product);

    public void deleteProduct(Long productId) throws ResourceNotFoundException;

    public Product updateProduct(Product product);
}
