package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Productrepository extends JpaRepository<Product, Long> {
    public List<Product> getProductsByCategory(Category category, Pageable pageable);
}
