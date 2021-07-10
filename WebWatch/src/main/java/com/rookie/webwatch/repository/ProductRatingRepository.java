package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {
    Optional<ProductRating> findByRatingNumber(long ratingNumber);
}
