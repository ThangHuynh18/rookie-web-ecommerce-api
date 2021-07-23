package com.rookie.webwatch.controller;


import com.rookie.webwatch.dto.*;

import com.rookie.webwatch.exception.*;

import com.rookie.webwatch.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAllProduct() throws GetDataFail {
        ResponseDTO response = new ResponseDTO();
        List<ResponseDTO> listResponse = new ArrayList<>();

        try {
            List<ProductResponseDTO> productDTOS = productService.retrieveProducts();
            List list = Collections.synchronizedList(new ArrayList(productDTOS));

            if (listResponse.addAll(list) == true) {
                response.setData(productDTOS);
            }
            response.setSuccessCode(SuccessCode.GET_ALL_PRODUCT_SUCCESS);
        } catch (Exception e) {
            throw new GetDataFail("" + ErrorCode.GET_PRODUCT_ERROR);
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findProductByCate(@RequestParam("category_id") @NotBlank Long categoryId,
                                                            PageDTO pageDTO
    ) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ProductDTO> productDTOS = productService.findProductByCate(categoryId, pageDTO);
        responseDTO.setData(productDTOS);
        responseDTO.setSuccessCode(SuccessCode.FIND_PRODUCT_SUCCESS);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/sort")
    public ResponseEntity<ResponseDTO> getProductSort(PageDTO pageDTO){
        ResponseDTO responseDTO = new ResponseDTO();
       List<ProductDTO> productDTO = productService.sortProduct(pageDTO);
       responseDTO.setData(productDTO);
       responseDTO.setSuccessCode(SuccessCode.GET_ALL_PRODUCT_SUCCESS);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<ResponseDTO> findProductByCategory(@PathVariable("category_id") @NotBlank Long cateId) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ProductDTO> productDTOS = productService.findProductByCategory(cateId);
        responseDTO.setData(productDTOS);
        responseDTO.setSuccessCode(SuccessCode.FIND_PRODUCT_SUCCESS);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/brand/{brand_id}")
    public ResponseEntity<ResponseDTO> findProductByBrand(@PathVariable("brand_id") @NotBlank Long brandId) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ProductDTO> productDTOS = productService.findProductByBrand(brandId);
        responseDTO.setData(productDTOS);
        responseDTO.setSuccessCode(SuccessCode.FIND_PRODUCT_SUCCESS);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ResponseDTO> getPro(@PathVariable("product_id") Long id) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<ProductDTO> productDTO = productService.getProduct(id);

            responseDTO.setData(productDTO);
            responseDTO.setSuccessCode(SuccessCode.FIND_PRODUCT_SUCCESS);
        } catch (Exception e){
            throw new ResourceNotFoundException(""+ErrorCode.FIND_PRODUCT_ERROR);
        }
        return ResponseEntity.ok(responseDTO);
    }

    //insert
    @PostMapping("/product")
    public ResponseEntity<ResponseDTO> createPro(@Valid @RequestBody ProductDTO dtoRequest) throws AddDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            //dtoRequest
            System.out.println("aaaaaaaaaaaaaaaaaaaaa"+dtoRequest);
            ProductDTO dto = productService.saveProduct(dtoRequest);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.ADD_PRODUCT_SUCCESS);
        } catch (Exception e){
            throw new AddDataFail(""+ErrorCode.ADD_PRODUCT_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

//    //update
    @PutMapping("/product/{product_id}")
    public ResponseEntity<ResponseDTO> updatePro(@PathVariable(value = "product_id") Long id, @Valid @RequestBody ProductDTO dtoRequest) throws UpdateDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
        ProductDTO updatePro = productService.updateProduct(id, dtoRequest);
            responseDTO.setData(updatePro);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_PRODUCT_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFail(""+ ErrorCode.UPDATE_PRODUCT_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

//    //delete
    @DeleteMapping("/product/{product_id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable(value = "product_id") Long productId) throws DeleteDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Boolean isDel = productService.deleteProduct(productId);
            responseDTO.setData(isDel);
            responseDTO.setSuccessCode(SuccessCode.DELETE_PRODUCT_SUCCESS);
        } catch (Exception e){
            throw new DeleteDataFail(""+ErrorCode.DELETE_PRODUCT_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
}
