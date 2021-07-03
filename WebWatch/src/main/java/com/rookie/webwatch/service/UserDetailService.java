package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.UserDetail;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {
    public List<UserDetail> retrieveUserDetails();

    public Optional<UserDetail> getUserDetail(Long udetailId);

    public UserDetail saveUserDetail(UserDetail udetail);

    public void deleteUserDetail(Long udetailId) throws ResourceNotFoundException;

    public UserDetail updateUserDetail(UserDetail udetail);
}
