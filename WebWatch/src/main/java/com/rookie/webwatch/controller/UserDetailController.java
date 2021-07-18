package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.ErrorCode;
import com.rookie.webwatch.dto.ResponseDTO;
import com.rookie.webwatch.dto.SuccessCode;
import com.rookie.webwatch.dto.UserDetailDTO;

import com.rookie.webwatch.exception.*;
import com.rookie.webwatch.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/udetails")
public class UserDetailController {
    @Autowired
    private UserDetailService detailService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAllUserDetail() throws GetDataFail {
        ResponseDTO response = new ResponseDTO();
        List<ResponseDTO> responseDTO = new ArrayList<>();
        try {
            List<UserDetailDTO> users = detailService.retrieveUserDetails();
            List list = Collections.synchronizedList(new ArrayList(users));

            if (responseDTO.addAll(list) == true) {
                response.setData(users);
            }
            response.setSuccessCode(SuccessCode.GET_ALL_USER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new GetDataFail(""+ ErrorCode.GET_USER_DETAIL_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{udetail_id}")
    public ResponseEntity<ResponseDTO> findUserDetail(@PathVariable("udetail_id") Long udetailId) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<UserDetailDTO> detailDTO = detailService.getUserDetail(udetailId);

            responseDTO.setData(detailDTO);
            responseDTO.setSuccessCode(SuccessCode.FIND_USER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new ResourceNotFoundException(""+ErrorCode.FIND_USER_DETAIL_ERROR);
        }
        return ResponseEntity.ok(responseDTO);
    }

    //save
    @PostMapping("/udetail")
    public ResponseEntity<ResponseDTO> createUserDetail(@Valid @RequestBody UserDetailDTO detailDTO) throws AddDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserDetailDTO dto = detailService.saveUserDetail(detailDTO);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.ADD_USER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new AddDataFail(""+ErrorCode.ADD_USER_DETAIL_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

//    //update
    @PutMapping("/udetail/{udetail_id}")
    public ResponseEntity<ResponseDTO> updateUserDetail(@PathVariable(value = "udetail_id") Long udetailId,
                                           @Valid @RequestBody UserDetailDTO detailDTO) throws UpdateDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserDetailDTO updateDetail = detailService.updateUserDetail(udetailId, detailDTO);

            responseDTO.setData(updateDetail);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_USER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFail(""+ErrorCode.UPDATE_USER_DETAIL_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

//    //delete
    @DeleteMapping("/udetail/{udetail_id}")
    public ResponseEntity<ResponseDTO> deleteUserDetail(@PathVariable(value = "udetail_id") Long udetailId) throws DeleteDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Boolean isDel = detailService.deleteUserDetail(udetailId);
            responseDTO.setData(isDel);
            responseDTO.setSuccessCode(SuccessCode.DELETE_USER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new DeleteDataFail(""+ErrorCode.DELETE_USER_DETAIL_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
}
