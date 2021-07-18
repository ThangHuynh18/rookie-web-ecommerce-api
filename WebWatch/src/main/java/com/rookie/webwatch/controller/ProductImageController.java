package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.ErrorCode;
import com.rookie.webwatch.dto.ImageDTO;
import com.rookie.webwatch.dto.ResponseDTO;
import com.rookie.webwatch.dto.SuccessCode;
import com.rookie.webwatch.exception.*;
import com.rookie.webwatch.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/images")
public class ProductImageController {
    @Autowired
    private ProductImageService imageService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAllImage() throws GetDataFail {
        ResponseDTO response = new ResponseDTO();
        List<ResponseDTO> responseDTO = new ArrayList<>();
        try {
            List<ImageDTO> images = imageService.retrieveProductImages();
            List list = Collections.synchronizedList(new ArrayList(images));

            if (responseDTO.addAll(list) == true) {
                response.setData(images);
            }
            response.setSuccessCode(SuccessCode.GET_ALL_PRODUCT_IMAGE_SUCCESS);
        } catch (Exception e){
            throw new GetDataFail(""+ ErrorCode.GET_PRODUCT_IMAGE_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{image_id}")
    public ResponseEntity<ResponseDTO> findProductImage(@PathVariable("image_id") Long imageId) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<ImageDTO> imageDTO = imageService.getProductImage(imageId);

            responseDTO.setData(imageDTO);
            responseDTO.setSuccessCode(SuccessCode.FIND_PRODUCT_IMAGE_SUCCESS);
        } catch (Exception e){
            throw new ResourceNotFoundException(""+ErrorCode.FIND_PRODUCT_IMAGE_ERROR);
        }
        return ResponseEntity.ok(responseDTO);
    }

    //insert
    @PostMapping("/image")
    public ResponseEntity<ResponseDTO>  createProductImage(@RequestBody ImageDTO imageDTO) throws AddDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            ImageDTO dto = imageService.saveProductImage(imageDTO);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.ADD_PRODUCT_IMAGE_SUCCESS);
        } catch (Exception e){
            throw new AddDataFail(""+ErrorCode.ADD_PRODUCT_IMAGE_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

//    //update
    @PutMapping("/image/{image_id}")
    public ResponseEntity<ResponseDTO> updateProductImage(@PathVariable(value = "image_id") Long imageId,
                                                 @RequestBody ImageDTO imageDTO) throws UpdateDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            ImageDTO updateImage = imageService.updateProductImage(imageId, imageDTO);

            responseDTO.setData(updateImage);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_PRODUCT_IMAGE_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFail(""+ErrorCode.UPDATE_PRODUCT_IMAGE_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

//    //delete
    @DeleteMapping("/image/{image_id}")
    public ResponseEntity<ResponseDTO> deleteProductImage(@PathVariable(value = "image_id") Long imageId) throws DeleteDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Boolean isDel = imageService.deleteProductImage(imageId);
            responseDTO.setData(isDel);
            responseDTO.setSuccessCode(SuccessCode.DELETE_PRODUCT_IMAGE_SUCCESS);
        } catch (Exception e){
            throw new DeleteDataFail(""+ErrorCode.DELETE_PRODUCT_IMAGE_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
}
