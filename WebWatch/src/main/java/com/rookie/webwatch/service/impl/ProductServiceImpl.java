package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.OrderDTO;
import com.rookie.webwatch.dto.ProductDTO;
import com.rookie.webwatch.entity.*;
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
@Transactional
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
    public ProductDTO saveProduct(ProductDTO productDTO) throws ResourceNotFoundException {

        Category category = categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(() ->
                new ResourceNotFoundException("category not found for this id: "+productDTO.getCategory_id()));

        Product product = new ProductDTO().convertToEti(productDTO);

        product = new Product(productDTO.getProductName(), productDTO.getProductPrice(), productDTO.getProductDescription(), productDTO.getProductQty());

        Set<String> strImage = productDTO.getProductImages();
        Set<ProductImage> images = new HashSet<>();

        if (strImage == null) {
            ProductImage proImage = imageRepository.findByImageLink(product.getProductImages().toString())
                    .orElseThrow(() -> new RuntimeException("Error: Image link is not found."));
            images.add(proImage);
        }
        product.setProductImages(images);


        Set<Long> longRating = productDTO.getProductRatings();
        Set<ProductRating> ratings = new HashSet<>();

//        if (longRating == null) {
////            ProductRating proRat = ratingRepository.findByRatingNumber(product.getProductRatings().)
////                    .orElseThrow(() -> new RuntimeException("Error: Image link is not found."));
//            ratings.add(productDTO.getProductRatings());
//        }
        product.setProductRatings(ratings);


        product.setCategory(category);

        return new ProductDTO().convertToDto(productrepository.save(product));
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
