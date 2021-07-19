package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.*;
import com.rookie.webwatch.entity.*;
import com.rookie.webwatch.exception.BadRequestException;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.*;
import com.rookie.webwatch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private BrandRepository brandRepository;

    @Override
    public List<ProductDTO> retrieveProducts() {
        List<Product> products = productrepository.findAll();
        return new ProductDTO().toListDto(products);
    }

    @Override
    public Optional<ProductDTO> getProduct(Long productId) throws ResourceNotFoundException {
        Product product = productrepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(""+ErrorCode.FIND_PRODUCT_ERROR));
        return Optional.of(new ProductDTO().convertToDto(product));
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) throws ResourceNotFoundException, BadRequestException {
        Product product = new ProductDTO().convertToEti(productDTO);

        Category category = categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(() ->
                new ResourceNotFoundException(""+ErrorCode.FIND_CATEGORY_ERROR));
        product.setCategory(category);

        Brand brand = brandRepository.findById(productDTO.getBrand_id()).orElseThrow(()->
                new ResourceNotFoundException(""+ErrorCode.FIND_BRAND_ERROR));
        product.setBrand(brand);

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
        return new ProductDTO().convertToDto(productrepository.findById(product.getProduct_id()).orElseThrow(()-> new ResourceNotFoundException(""+ErrorCode.FIND_PRODUCT_ERROR)));
    }

    @Override
    public Boolean deleteProduct(Long productId) throws ResourceNotFoundException {
        Product product = productrepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(""+ErrorCode.FIND_PRODUCT_ERROR));
        this.productrepository.delete(product);
        return true;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) throws ResourceNotFoundException {
        Product proExist = productrepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException(""+ErrorCode.FIND_PRODUCT_ERROR));

        Category category = categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(() ->
                new ResourceNotFoundException(""+ErrorCode.FIND_CATEGORY_ERROR));

        Brand brand = brandRepository.findById(productDTO.getBrand_id()).orElseThrow(()->
                new ResourceNotFoundException(""+ErrorCode.FIND_BRAND_ERROR));
        proExist.setProductName(productDTO.getProductName());
        proExist.setProductPrice(productDTO.getProductPrice());
        proExist.setProductDescription(productDTO.getProductDescription());
        proExist.setProductQty(productDTO.getProductQty());

        proExist.setCategory(category);
        proExist.setBrand(brand);

        Product product = new Product();
        product = productrepository.save(proExist);
        return new ProductDTO().convertToDto(product);
    }


    @Override
    public List<ProductDTO> sortProduct(PageDTO pageDTO) {
        List<Product> list = null;
        Page<Product> page = null;

        Sort sort = Sort.by(pageDTO.getSort(), pageDTO.getSortBy());
        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize(), sort);

        list = productrepository.findAll(pageable).getContent();
        page = new PageImpl(list);

        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS = new ProductDTO().toListDto(page.getContent());

        return productDTOS;

        //return productrepository.findAll(pageable);

    }

    @Override
    public List<ProductDTO> findProductByCate(Long categoryId, PageDTO pageDTO) throws ResourceNotFoundException {
        Optional<Category> cateExist = categoryRepository.findById(categoryId);
        if(!cateExist.isPresent()){
            throw new ResourceNotFoundException(""+ ErrorCode.FIND_CATEGORY_ERROR);
        }
        Category category = cateExist.get();

        Sort sort = Sort.by(pageDTO.getSort(), pageDTO.getSortBy());
        Pageable pageRequest = PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize(), sort);

        List<Product> list = null;
        Page<Product> page = null;
        list = productrepository.getProductsByCategory(category, pageRequest);
        page = new PageImpl(list);
        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS = new ProductDTO().toListDto(page.getContent());
        return productDTOS;
    }

}
