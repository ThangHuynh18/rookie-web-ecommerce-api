package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
