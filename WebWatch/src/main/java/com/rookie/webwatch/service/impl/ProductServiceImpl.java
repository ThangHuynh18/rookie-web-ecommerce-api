package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.ImageDTO;
import com.rookie.webwatch.dto.OrderDTO;
import com.rookie.webwatch.dto.ProductDTO;
import com.rookie.webwatch.dto.RatingDTO;
import com.rookie.webwatch.entity.*;
import com.rookie.webwatch.exception.BadRequestException;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.CategoryRepository;
import com.rookie.webwatch.repository.ProductImageRepository;
import com.rookie.webwatch.repository.ProductRatingRepository;
import com.rookie.webwatch.repository.Productrepository;
import com.rookie.webwatch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
//@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private Productrepository productrepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductImageRepository imageRepository;

    @Autowired
    private ProductRatingRepository ratingRepository;

    @Override
    public List<ProductDTO> retrieveProducts() {
        List<Product> products = productrepository.findAll();
        return new ProductDTO().toListDto(products);
    }

    @Override
    public ProductDTO getProduct(Long productId) throws ResourceNotFoundException {
        Product product = productrepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found for this id: "+productId));
        return new ProductDTO().convertToDto(product);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) throws ResourceNotFoundException, BadRequestException {
        Product product = new ProductDTO().convertToEti(productDTO);

        Category category = categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(() ->
                new ResourceNotFoundException("category not found for this id: "+productDTO.getCategory_id()));

        product.setCategory(category);

        try {
            product = productrepository.save(product);

//        List<ProductRating> ratings = new RatingDTO().toListEntity(productDTO.getRatingDTOS());
//        Product productRa = product;
//        ratings.forEach(r -> {
//            r.setProduct(productRa);
//            ratingRepository.save(r);
//        });

            List<ProductImage> images = new ImageDTO().toListEntity(productDTO.getImageDTOS());
            Product productFinal = product;
            images.forEach(i -> {
                i.setProduct(productFinal);
                //System.out.println("--------------------------"+ i.getImageLink()+"___"+i.getProduct().getProduct_id());
                imageRepository.save(i);
            });
        } catch (Exception e){
            throw new BadRequestException("invalid request "+e.getMessage());
        }
        return new ProductDTO().convertToDto(productrepository.findById(product.getProduct_id()).orElseThrow(()-> new ResourceNotFoundException("product not found for this id")));
    }

    @Override
    public void deleteProduct(Long productId) throws ResourceNotFoundException {
        Product product = productrepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found for this id: " + productId));
        this.productrepository.delete(product);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) throws ResourceNotFoundException {
        Product proExist = productrepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("product not found for this id: "+productId));

        Category category = categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(() ->
                new ResourceNotFoundException("category not found for this id: "+productDTO.getCategory_id()));

        proExist.setProductName(productDTO.getProductName());
        proExist.setProductPrice(productDTO.getProductPrice());
        proExist.setProductDescription(productDTO.getProductDescription());
        proExist.setProductQty(productDTO.getProductQty());

        proExist.setCategory(category);

        Product product = new Product();
        product = productrepository.save(proExist);
        return new ProductDTO().convertToDto(product);
    }

}
