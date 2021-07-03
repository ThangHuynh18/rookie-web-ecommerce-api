package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {
}
