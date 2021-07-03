package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.UserDetail;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailService{

    @Autowired
    private UserDetailRepository detailRepository;

    @Override
    public List<UserDetail> retrieveUserDetails() {
        List<UserDetail> userDetails = detailRepository.findAll();
        return userDetails;
    }

    @Override
    public Optional<UserDetail> getUserDetail(Long udetailId) {
        return detailRepository.findById(udetailId);
    }

    @Override
    public UserDetail saveUserDetail(UserDetail udetail) {
        return detailRepository.save(udetail);
    }

    @Override
    public void deleteUserDetail(Long udetailId) throws ResourceNotFoundException {
        UserDetail userDetail = detailRepository.findById(udetailId).orElseThrow(() -> new ResourceNotFoundException("user_detail not found for this id: " + udetailId));
        this.detailRepository.delete(userDetail);
    }

    @Override
    public UserDetail updateUserDetail(UserDetail udetail) {
        return detailRepository.save(udetail);
    }
}
