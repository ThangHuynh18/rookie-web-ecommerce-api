package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.UserDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserDTO> getAllUser(){
        List<UserDTO> userDTOS = userService.retrieveUsers();
        return userDTOS;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable("user_id") Long userId) throws ResourceNotFoundException {
        UserDTO userDTO = userService.getUser(userId);

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO dto = userService.saveUser(userDTO);
        return ResponseEntity.ok(dto);
    }

//    //update
    @PutMapping("/user/{user_id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "user_id") Long userId,
                                                 @RequestBody UserDTO userDTO) throws ResourceNotFoundException{
        UserDTO updateUser = userService.updateUser(userId, userDTO);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

//    //delete
    @DeleteMapping("/user/{user_id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "user_id") Long userId)
            throws ResourceNotFoundException {
        userService.deleteUser(userId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }

}
