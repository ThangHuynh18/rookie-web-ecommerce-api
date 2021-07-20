package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrderByUser(User user);
}
