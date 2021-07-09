package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.UserDTO;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserDTO> retrieveUsers();

    public UserDTO getUser(Long userId) throws ResourceNotFoundException;

    public UserDTO saveUser(UserDTO userDTO);

    public void deleteUser(Long userId) throws ResourceNotFoundException;

    UserDTO updateUser(Long userId, UserDTO userDTO) throws ResourceNotFoundException;

}
