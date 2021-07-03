package com.rookie.webwatch.controller;

import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.entity.UserDetail;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/udetails")
public class UserDetailController {
    @Autowired
    private UserDetailService detailService;

    @GetMapping("")
    public List<UserDetail> getAllUserDetail(){
        List<UserDetail> users = detailService.retrieveUserDetails();
        return users;
    }

    @GetMapping("/{udetail_id}")
    public Optional<UserDetail> findUserDetail(@PathVariable("udetail_id") Long udetailId) throws ResourceNotFoundException {
        Optional<UserDetail> user = Optional.ofNullable(detailService.getUserDetail(udetailId)
                .orElseThrow(() -> new ResourceNotFoundException("user detail not found for this id: " + udetailId)));

        return detailService.getUserDetail(udetailId);
    }

    //save employee
    @PostMapping("/udetail")
    public UserDetail createUserDetail(@RequestBody UserDetail user){
        return detailService.saveUserDetail(user);
    }
    //
//    //update
    @PutMapping("/udetail/{udetail_id}")
    public ResponseEntity<UserDetail> updateUserDetail(@PathVariable(value = "udetail_id") Long udetailId,
                                           @RequestBody UserDetail detail) throws ResourceNotFoundException{
        UserDetail user = detailService.getUserDetail(udetailId).orElseThrow(() -> new ResourceNotFoundException("user detail not found for this id: " +udetailId));

        user.setUdetailPhone(detail.getUdetailPhone());
        user.setUdetailAddress(detail.getUdetailAddress());

        //user.setUser(detail.getUser());

        return ResponseEntity.ok(detailService.updateUserDetail(user));
    }
    //
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
