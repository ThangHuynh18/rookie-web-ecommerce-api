package com.rookie.webwatch.controller;


import com.rookie.webwatch.dto.ProductDTO;

import com.rookie.webwatch.exception.BadRequestException;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import com.rookie.webwatch.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<ProductDTO> getAllProduct(){
        List<ProductDTO> productDTOS = productService.retrieveProducts();

        return productDTOS;
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductDTO> getPro(@PathVariable("product_id") Long id) throws ResourceNotFoundException {
        ProductDTO productDTO = productService.getProduct(id);

        return ResponseEntity.ok(productDTO);
    }

    //insert
    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createPro(@Valid @RequestBody ProductDTO dtoRequest) throws ResourceNotFoundException, BadRequestException {
        ProductDTO dto = productService.saveProduct(dtoRequest);
        return ResponseEntity.ok(dto);
    }

//    //update
    @PutMapping("/product/{product_id}")
    public ResponseEntity<ProductDTO> updatePro(@PathVariable(value = "product_id") Long id, @Valid @RequestBody ProductDTO dtoRequest) throws ResourceNotFoundException {
        ProductDTO updatePro = productService.updateProduct(id, dtoRequest);

        return new ResponseEntity<>(updatePro, HttpStatus.OK);
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
}
