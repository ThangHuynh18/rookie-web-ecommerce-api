package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
