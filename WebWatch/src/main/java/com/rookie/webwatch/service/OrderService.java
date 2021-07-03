package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> retrieveOrders();

    public Optional<Order> getOrder(Long orderId);

    public Order saveOrder(Order order);

    public void deleteOrder(Long orderId) throws ResourceNotFoundException;

    public Order updateOrder(Order order);
}
