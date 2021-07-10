package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.ImageDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;

public interface ProductImageService {
    public List<ImageDTO> retrieveProductImages();

    public ImageDTO getProductImage(Long imageId) throws ResourceNotFoundException;

    public ImageDTO saveProductImage(ImageDTO imageDTO) throws ResourceNotFoundException;

    public void deleteProductImage(Long imageId) throws ResourceNotFoundException;

    public ImageDTO updateProductImage(Long id, ImageDTO imageDTO) throws ResourceNotFoundException;
}
