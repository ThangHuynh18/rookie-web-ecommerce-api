package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findOrderDetailsByOrder(Order order);
}
