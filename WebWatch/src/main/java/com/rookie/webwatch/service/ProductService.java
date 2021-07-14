package com.rookie.webwatch.service;


import com.rookie.webwatch.dto.ProductDTO;
import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.exception.BadRequestException;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<ProductDTO> retrieveProducts();

    public Optional<ProductDTO> getProduct(Long productId) throws ResourceNotFoundException;

    public ProductDTO saveProduct(ProductDTO productDTO) throws ResourceNotFoundException, BadRequestException;

    public void deleteProduct(Long productId) throws ResourceNotFoundException;

    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) throws ResourceNotFoundException;

}
