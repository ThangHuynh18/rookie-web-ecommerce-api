package com.rookie.webwatch.convert;

import com.rookie.webwatch.dto.UserDTO;
import com.rookie.webwatch.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConvert {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto(Optional<User> user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setUserName(user.get().getUserName());
        userDTO.setUserEmail(user.get().getUserEmail());
        userDTO.setUserPassword(user.get().getUserPassword());

        return userDTO;
    }


    public UserDTO convertToDtoForUpdate(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setUserName(user.getUserName());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserPassword(user.getUserPassword());

        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        return user;
    }
}
