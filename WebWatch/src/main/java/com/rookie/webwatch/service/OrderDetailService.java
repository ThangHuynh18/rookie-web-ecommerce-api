package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.OrderDetailDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;

public interface OrderDetailService {
    public List<OrderDetailDTO> retrieveOrderDetails();

    public OrderDetailDTO getOrderDetail(Long detailId) throws ResourceNotFoundException;

    public OrderDetailDTO saveOrderDetail(OrderDetailDTO detailDTO) throws ResourceNotFoundException;

    public void deleteOrderDetail(Long detailId) throws ResourceNotFoundException;

    public OrderDetailDTO updateOrderDetail(Long id, OrderDetailDTO detailDTO) throws ResourceNotFoundException;
}
