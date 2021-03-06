package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.OrderDTO;
import com.rookie.webwatch.dto.OrderResponseDTO;
import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<OrderResponseDTO> retrieveOrders();

    public Optional<OrderDTO> getOrder(Long orderId) throws ResourceNotFoundException;

    public OrderDTO saveOrder(OrderDTO order) throws ResourceNotFoundException;

    public Boolean deleteOrder(Long orderId) throws ResourceNotFoundException;

    public OrderDTO updateOrder(Long orderId,OrderDTO order) throws ResourceNotFoundException;

    public List<OrderResponseDTO> findOrderByUser(Long userId) throws ResourceNotFoundException;

    public OrderDTO updateStatusOrder(Long orderId, String status) throws ResourceNotFoundException;
}
