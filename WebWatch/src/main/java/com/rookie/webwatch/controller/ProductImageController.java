package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.ImageDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ProductImageController {
    @Autowired
    private ProductImageService imageService;

    @GetMapping("")
    public List<ImageDTO> getAllImage(){
        List<ImageDTO> images = imageService.retrieveProductImages();
        return images;
    }

    @GetMapping("/{image_id}")
    public ResponseEntity<ImageDTO> findProductImage(@PathVariable("image_id") Long imageId) throws ResourceNotFoundException {
        ImageDTO imageDTO = imageService.getProductImage(imageId);

        return ResponseEntity.ok(imageDTO);
    }

    //save employee
    @PostMapping("/image")
    public ResponseEntity<ImageDTO>  createProductImage(@RequestBody ImageDTO imageDTO) throws ResourceNotFoundException {
        ImageDTO dto = imageService.saveProductImage(imageDTO);
        return ResponseEntity.ok(dto);
    }

//    //update
    @PutMapping("/image/{image_id}")
    public ResponseEntity<ImageDTO> updateProductImage(@PathVariable(value = "image_id") Long imageId,
                                                 @RequestBody ImageDTO imageDTO) throws ResourceNotFoundException{
        ImageDTO updateImage = imageService.updateProductImage(imageId, imageDTO);

        return new ResponseEntity<>(updateImage, HttpStatus.OK);
    }

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
