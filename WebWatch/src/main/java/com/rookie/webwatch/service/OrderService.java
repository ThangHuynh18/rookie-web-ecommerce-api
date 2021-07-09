package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.OrderDTO;
import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<OrderDTO> retrieveOrders();

    public OrderDTO getOrder(Long orderId) throws ResourceNotFoundException;

    public OrderDTO saveOrder(OrderDTO order) throws ResourceNotFoundException;

    public void deleteOrder(Long orderId) throws ResourceNotFoundException;

    public OrderDTO updateOrder(Long orderId,OrderDTO order) throws ResourceNotFoundException;
}
