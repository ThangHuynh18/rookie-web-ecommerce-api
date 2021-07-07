package com.rookie.webwatch.controller;

import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAllUser(){
        List<User> users = userService.retrieveUsers();
        return users;
    }

    @GetMapping("/{user_id}")
    public Optional<User> findUser(@PathVariable("user_id") Long userId) throws ResourceNotFoundException {
        Optional<User> user = Optional.ofNullable(userService.getUser(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId)));

        return userService.getUser(userId);
    }

    //save employee
    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    //
//    //update
    @PutMapping("/user/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "user_id") Long userId,
                                                 @RequestBody User userDetail) throws ResourceNotFoundException{
        User user = userService.getUser(userId).orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " +userId));

        user.setUserName(userDetail.getUserName());
        user.setUserEmail(userDetail.getUserEmail());
        user.setUserPassword(userDetail.getUserPassword());
        user.setRoles(userDetail.getRoles());

//        user.setOrders(userDetail.getOrders());
//        user.setRatings(userDetail.getRatings());

        return ResponseEntity.ok(userService.updateUser(user));
    }
    //
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
