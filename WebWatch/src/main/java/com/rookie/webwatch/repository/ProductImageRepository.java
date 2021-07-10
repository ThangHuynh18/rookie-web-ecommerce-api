package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    Optional<ProductImage> findByImageLink(String imageLink);
}
