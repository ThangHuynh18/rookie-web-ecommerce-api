package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.UserDetailDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/udetails")
public class UserDetailController {
    @Autowired
    private UserDetailService detailService;

    @GetMapping("")
    public List<UserDetailDTO> getAllUserDetail(){
        List<UserDetailDTO> users = detailService.retrieveUserDetails();
        return users;
    }

    @GetMapping("/{udetail_id}")
    public ResponseEntity<UserDetailDTO> findUserDetail(@PathVariable("udetail_id") Long udetailId) throws ResourceNotFoundException {
        UserDetailDTO detailDTO = detailService.getUserDetail(udetailId);

        return ResponseEntity.ok(detailDTO);
    }

    //save
    @PostMapping("/udetail")
    public ResponseEntity<UserDetailDTO> createUserDetail(@RequestBody UserDetailDTO detailDTO){
        UserDetailDTO dto = detailService.saveUserDetail(detailDTO);
        return ResponseEntity.ok(dto);
    }

//    //update
    @PutMapping("/udetail/{udetail_id}")
    public ResponseEntity<UserDetailDTO> updateUserDetail(@PathVariable(value = "udetail_id") Long udetailId,
                                           @RequestBody UserDetailDTO detailDTO) throws ResourceNotFoundException{
        UserDetailDTO updateDetail = detailService.updateUserDetail(udetailId, detailDTO);

        return new ResponseEntity<>(updateDetail, HttpStatus.OK);
    }

//    //delete
    @DeleteMapping("/udetail/{udetail_id}")
    public Map<String, Boolean> deleteUserDetail(@PathVariable(value = "udetail_id") Long udetailId)
            throws ResourceNotFoundException {
        detailService.deleteUserDetail(udetailId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }
}
