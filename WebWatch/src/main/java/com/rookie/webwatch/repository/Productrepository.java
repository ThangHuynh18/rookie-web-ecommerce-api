package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productrepository extends JpaRepository<Product, Long> {
}
