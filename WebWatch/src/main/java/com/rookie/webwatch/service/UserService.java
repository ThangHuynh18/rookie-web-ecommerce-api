package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> retrieveUsers();

    public Optional<User> getUser(Long userId);

    public User saveUser(User user);

    public void deleteUser(Long userId) throws ResourceNotFoundException;

    public User updateUser(User user);


}
