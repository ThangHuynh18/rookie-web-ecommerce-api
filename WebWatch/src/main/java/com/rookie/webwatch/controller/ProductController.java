package com.rookie.webwatch.controller;

import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> getAllProduct(){
        List<Product> products = productService.retrieveProducts();
        return products;
    }

    @GetMapping("/{product_id}")
    public Optional<Product> findProduct(@PathVariable("product_id") Long productId) throws ResourceNotFoundException {
        Optional<Product> category = Optional.ofNullable(productService.getProduct(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + productId)));

        return productService.getProduct(productId);
    }

    //save employee
    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    //
//    //update
    @PutMapping("/product/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "product_id") Long productId,
                                                   @RequestBody Product productDetail) throws ResourceNotFoundException{
        Product product = productService.getProduct(productId).orElseThrow(() -> new ResourceNotFoundException("product not found for this id: " +productId));

        product.setProductName(productDetail.getProductName());
        product.setProductPrice(productDetail.getProductPrice());
        product.setProductDesciption(productDetail.getProductDesciption());
        product.setProductQty(productDetail.getProductQty());
        product.setCategory(productDetail.getCategory());


        return ResponseEntity.ok(productService.updateProduct(product));
    }
    //
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
