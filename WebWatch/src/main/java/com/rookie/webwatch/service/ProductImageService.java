package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.ProductImage;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductImageService {
    public List<ProductImage> retrieveProductImages();

    public Optional<ProductImage> getProductImage(Long imageId);

    public ProductImage saveProductImage(ProductImage productImage);

    public void deleteProductImage(Long imageId) throws ResourceNotFoundException;

    public ProductImage updateProductImage(ProductImage productImage);
}
