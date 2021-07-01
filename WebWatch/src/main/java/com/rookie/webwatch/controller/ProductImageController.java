package com.rookie.webwatch.controller;

import com.rookie.webwatch.entity.ProductImage;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ProductImageController {
    @Autowired
    private ProductImageService imageService;

    @GetMapping("")
    public List<ProductImage> getAllImage(){
        List<ProductImage> images = imageService.retrieveProductImages();
        return images;
    }

    @GetMapping("/{image_id}")
    public Optional<ProductImage> findProductImage(@PathVariable("image_id") Long imageId) throws ResourceNotFoundException {
        Optional<ProductImage> image = Optional.ofNullable(imageService.getProductImage(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found for this id: " + imageId)));

        return imageService.getProductImage(imageId);
    }

    //save employee
    @PostMapping("/image")
    public ProductImage createProductImage(@RequestBody ProductImage image){
        return imageService.saveProductImage(image);
    }
    //
//    //update
    @PutMapping("/image/{image_id}")
    public ResponseEntity<ProductImage> updateProductImage(@PathVariable(value = "image_id") Long imageId,
                                                 @RequestBody ProductImage imageDetail) throws ResourceNotFoundException{
        ProductImage image = imageService.getProductImage(imageId).orElseThrow(() -> new ResourceNotFoundException("image not found for this id: " +imageId));

        image.setImageLink(imageDetail.getImageLink());
        image.setProduct(imageDetail.getProduct());

        return ResponseEntity.ok(imageService.updateProductImage(image));
    }
    //
//    //delete
    @DeleteMapping("/image/{image_id}")
    public Map<String, Boolean> deleteProductImage(@PathVariable(value = "image_id") Long imageId)
            throws ResourceNotFoundException {
        imageService.deleteProductImage(imageId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }
}
