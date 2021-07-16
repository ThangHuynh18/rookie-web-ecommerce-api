package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.UserDetailDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {
    public List<UserDetailDTO> retrieveUserDetails();

    public Optional<UserDetailDTO> getUserDetail(Long udetailId) throws ResourceNotFoundException;

    public UserDetailDTO saveUserDetail(UserDetailDTO detailDTO) throws ResourceNotFoundException;

    public Boolean deleteUserDetail(Long udetailId) throws ResourceNotFoundException;

    public UserDetailDTO updateUserDetail(Long id, UserDetailDTO detailDTO) throws ResourceNotFoundException;
}
