package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.CategoryDTO;
import com.rookie.webwatch.dto.ErrorCode;
import com.rookie.webwatch.dto.ResponseDTO;
import com.rookie.webwatch.dto.SuccessCode;
import com.rookie.webwatch.exception.AddDataFail;
import com.rookie.webwatch.exception.DeleteDataFail;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.exception.UpdateDataFail;
import com.rookie.webwatch.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAllCategory(){
        ResponseDTO response = new ResponseDTO();
        List<ResponseDTO> responseDTO = new ArrayList<>();

        List<CategoryDTO> categoryDTOS = categoryService.retrieveCategories();
        List list = Collections.synchronizedList(new ArrayList(categoryDTOS));

        //response.setData(responseDTO.addAll(list));
        if(responseDTO.addAll(list) == true){
            response.setData(categoryDTOS);
        }
        response.setSuccessCode(SuccessCode.GET_ALL_CATEGORY_SUCCESS);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{category_id}")
    public ResponseEntity<ResponseDTO> getCate(@PathVariable("category_id") Long id) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        Optional<CategoryDTO> categoryDTO = categoryService.getCate(id);

        responseDTO.setData(categoryDTO);
        responseDTO.setSuccessCode(SuccessCode.FIND_CATEGORY_SUCCESS);

        return ResponseEntity.ok(responseDTO);
    }

    //insert

    @PostMapping("/category")
    public ResponseEntity<ResponseDTO> createCate(@Valid @RequestBody CategoryDTO categoryDTO) throws AddDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            CategoryDTO dto = categoryService.saveCategory(categoryDTO);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.ADD_CATEGORY_SUCCESS);
        } catch (Exception e){
            throw new AddDataFail(""+ErrorCode.ADD_CATEGORY_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

    //update
    @PutMapping("/category/{category_id}")
    public ResponseEntity<ResponseDTO> updateCate(@PathVariable(value = "category_id") Long categoryId, @Valid @RequestBody CategoryDTO categoryDTO) throws UpdateDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            CategoryDTO updateCate = categoryService.updateCategory(categoryId, categoryDTO);

            responseDTO.setData(updateCate);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_CATEGORY_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFail(""+ErrorCode.UPDATE_CATEGORY_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

//    //delete
    @DeleteMapping("/category/{category_id}")
    public ResponseEntity<ResponseDTO> deleteCate(@PathVariable(value = "category_id") Long categoryId) throws DeleteDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Boolean isDel = categoryService.deleteCategory(categoryId);
            responseDTO.setData(isDel);
            responseDTO.setSuccessCode(SuccessCode.DELETE_CATEGORY_SUCCESS);
        } catch (Exception e){
            throw new DeleteDataFail(""+ErrorCode.DELETE_CATEGORY_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
}
