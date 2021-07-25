package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    List<UserDetail> getUserDetailByUser(User user);
}
