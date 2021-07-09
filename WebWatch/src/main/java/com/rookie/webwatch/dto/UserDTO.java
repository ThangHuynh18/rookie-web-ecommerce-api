package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private long user_id;
    private String userName;
    private String userEmail;
    private String userPassword;

    private Set<RoleDTO> roleDTOS;

    public UserDTO() {
    }


    public UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserPassword(user.getUserPassword());

        //userDTO.setRoleDTOS(user.getRoles());

        return userDTO;
    }

    public User convertToEti(UserDTO userDTO) {
        User user = new User();

        user.setUserName(userDTO.getUserName());
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserPassword(userDTO.getUserPassword());

        return user;
    }


    public List<UserDTO> toListDto(List<User> listEntity) {
        List<UserDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<RoleDTO> getRoleDTOS() {
        return roleDTOS;
    }

    public void setRoleDTOS(Set<RoleDTO> roleDTOS) {
        this.roleDTOS = roleDTOS;
    }
}
