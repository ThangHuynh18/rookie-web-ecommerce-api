package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.OrderDTO;
import com.rookie.webwatch.dto.UserDTO;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.entity.Status;
import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> retrieveUsers() {
        List<User> users = userRepository.findAll();
        return new UserDTO().toListDto(users);
    }

    @Override
    public UserDTO getUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found for this id: "+userId));
        return new UserDTO().convertToDto(user);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = new UserDTO().convertToEti(userDTO);

        return new UserDTO().convertToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));
        this.userRepository.delete(user);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) throws ResourceNotFoundException {
        User userExist = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("user not found for this id: "+userId));

        userExist.setUserName(userDTO.getUserName());
        userExist.setUserEmail(userDTO.getUserEmail());
        userExist.setUserPassword(userDTO.getUserPassword());

        User user = new User();
        user = userRepository.save(userExist);
        return new UserDTO().convertToDto(user);
    }

}
