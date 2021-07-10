package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.UserDetailDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;

public interface UserDetailService {
    public List<UserDetailDTO> retrieveUserDetails();

    public UserDetailDTO getUserDetail(Long udetailId) throws ResourceNotFoundException;

    public UserDetailDTO saveUserDetail(UserDetailDTO detailDTO) throws ResourceNotFoundException;

    public void deleteUserDetail(Long udetailId) throws ResourceNotFoundException;

    public UserDetailDTO updateUserDetail(Long id, UserDetailDTO detailDTO) throws ResourceNotFoundException;
}
