package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.OrderDetailDTO;

import com.rookie.webwatch.dto.OrderDetailResponseDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    public List<OrderDetailDTO> retrieveOrderDetails();

    public Optional<OrderDetailDTO> getOrderDetail(Long detailId) throws ResourceNotFoundException;

    public OrderDetailDTO saveOrderDetail(OrderDetailDTO detailDTO) throws ResourceNotFoundException;

    public Boolean deleteOrderDetail(Long detailId) throws ResourceNotFoundException;

    public OrderDetailDTO updateOrderDetail(Long id, OrderDetailDTO detailDTO) throws ResourceNotFoundException;

    public List<OrderDetailResponseDTO> findDetailByOrder(Long orderId) throws ResourceNotFoundException;

    public OrderDetailDTO restoreQty(Long productId) throws ResourceNotFoundException;
}
