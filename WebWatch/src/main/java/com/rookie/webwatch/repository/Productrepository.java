package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Brand;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface Productrepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> getProductsByCategory(Category category, Pageable pageable);
    List<Product> findProductByCategory(Category category);
    List<Product> findProductByBrand(Brand brand);

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
    List<Product> findAll(Specification<Product> spec);

    Page<Product> findByProductNameContaining(String name, Pageable pageable);
}
