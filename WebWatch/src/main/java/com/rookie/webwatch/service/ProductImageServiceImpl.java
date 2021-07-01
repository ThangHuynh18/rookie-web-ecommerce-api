package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.entity.ProductImage;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService{
    @Autowired
    private ProductImageRepository imageRepository;

    @Override
    public List<ProductImage> retrieveProductImages() {
        List<ProductImage> productImages = imageRepository.findAll();
        return productImages;
    }

    @Override
    public Optional<ProductImage> getProductImage(Long imageId) {
        return imageRepository.findById(imageId);
    }

    @Override
    public ProductImage saveProductImage(ProductImage productImage) {
        return imageRepository.save(productImage);
    }

    @Override
    public void deleteProductImage(Long imageId) throws ResourceNotFoundException {
        ProductImage productImage = imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("iamge not found for this id: " + imageId));
        this.imageRepository.delete(productImage);
    }

    @Override
    public ProductImage updateProductImage(ProductImage productImage) {
        return imageRepository.save(productImage);
    }
}
