package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.ImageDTO;
import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.entity.ProductImage;

import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.ProductImageRepository;
import com.rookie.webwatch.repository.Productrepository;
import com.rookie.webwatch.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageRepository imageRepository;

    @Autowired
    private Productrepository productrepository;

    @Override
    public List<ImageDTO> retrieveProductImages() {
        List<ProductImage> productImages = imageRepository.findAll();
        return new ImageDTO().toListDto(productImages);
    }

    @Override
    public ImageDTO getProductImage(Long imageId) throws ResourceNotFoundException {
        ProductImage image = imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("image not found for this id: "+imageId));
        return new ImageDTO().convertToDto(image);
    }

    @Override
    public ImageDTO saveProductImage(ImageDTO imageDTO) throws ResourceNotFoundException {
        Product product = productrepository.findById(imageDTO.getProduct_id()).orElseThrow(() ->
                new ResourceNotFoundException("product not found for this id: "+imageDTO.getProduct_id()));

        ProductImage image = new ImageDTO().convertToEti(imageDTO);
        image.setProduct(product);

        return new ImageDTO().convertToDto(imageRepository.save(image));
    }

    @Override
    public void deleteProductImage(Long imageId) throws ResourceNotFoundException {
        ProductImage productImage = imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("iamge not found for this id: " + imageId));
        this.imageRepository.delete(productImage);
    }

    @Override
    public ImageDTO updateProductImage(Long id, ImageDTO imageDTO) throws ResourceNotFoundException {
        ProductImage imageExist = imageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("image not found for this id: "+id));

        imageExist.setImageLink(imageDTO.getImageLink());

        ProductImage image = new ProductImage();
        image = imageRepository.save(imageExist);
        return new ImageDTO().convertToDto(image);
    }
}
