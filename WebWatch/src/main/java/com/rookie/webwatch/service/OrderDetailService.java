package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.OrderDetail;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    public List<OrderDetail> retrieveOrderDetails();

    public Optional<OrderDetail> getOrderDetail(Long detailId);

    public OrderDetail saveOrderDetail(OrderDetail detail);

    public void deleteOrderDetail(Long detailId) throws ResourceNotFoundException;

    public OrderDetail updateOrderDetail(OrderDetail detail);
}
