package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
