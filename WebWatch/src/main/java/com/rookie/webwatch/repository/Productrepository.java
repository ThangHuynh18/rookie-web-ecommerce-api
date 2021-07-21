package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Brand;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Productrepository extends JpaRepository<Product, Long> {
    List<Product> getProductsByCategory(Category category, Pageable pageable);
    List<Product> findProductByCategory(Category category);
    List<Product> findProductByBrand(Brand brand);
}
