package com.rookie.webwatch.controller;

import com.rookie.webwatch.convert.ProductConvert;
import com.rookie.webwatch.dto.CategoryDTO;
import com.rookie.webwatch.dto.request.ProductDtoRequest;
import com.rookie.webwatch.dto.response.ProductDtoResponse;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.CategoryService;
import com.rookie.webwatch.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductConvert productConvert;

    @GetMapping("")
    public List<ProductDtoResponse> getAllProduct(){
        List<Product> products = productService.retrieveProducts();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{product_id}")
    public ProductDtoResponse getPro(@PathVariable("product_id") Long id) throws ResourceNotFoundException {
        Product product = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("product not found for this id: " +id));

        return productConvert.convertToDto(productService.getProduct(id));
    }

    //save employee
    @PostMapping("/product")
    public ProductDtoResponse createPro(@RequestBody ProductDtoRequest dtoRequest) throws ResourceNotFoundException {
        Product product = productConvert.convertToEntity(dtoRequest);

        long cateIdReq = dtoRequest.getCategory_id();
        product.setCategory(categoryService.getCateById(cateIdReq));

        Product proCreate = productService.saveProduct(product);
        return productConvert.convertToDto(Optional.ofNullable(proCreate));
    }

//    //update
    @PutMapping("/product/{product_id}")
    public ResponseEntity<ProductDtoResponse> updatePro(@PathVariable(value = "product_id") Long id, @RequestBody ProductDtoRequest dtoRequest) throws ResourceNotFoundException {
        Product product = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("product not found for this id: " +id));
        product.setProductName(dtoRequest.getProductName());
        product.setProductPrice(dtoRequest.getProductPrice());
        product.setProductDescription(dtoRequest.getProductDescription());
        product.setProductQty(dtoRequest.getProductQty());

        return ResponseEntity.ok(productConvert.convertToDtoForUpdate(productService.updateProduct(product)));
    }

//    //delete
    @DeleteMapping("/product/{product_id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "product_id") Long productId)
            throws ResourceNotFoundException {
        productService.deleteProduct(productId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }

    private ProductDtoResponse convertToDto(Product product) {

        return modelMapper.map(product, ProductDtoResponse.class);
    }
}
