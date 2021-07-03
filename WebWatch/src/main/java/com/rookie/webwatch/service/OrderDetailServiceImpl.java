package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.OrderDetail;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService{

    @Autowired
    private OrderDetailRepository detailRepository;

    @Override
    public List<OrderDetail> retrieveOrderDetails() {
        List<OrderDetail> details = detailRepository.findAll();
        return details;
    }

    @Override
    public Optional<OrderDetail> getOrderDetail(Long detailId) {
        return detailRepository.findById(detailId);
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetail detail) {
        return detailRepository.save(detail);
    }

    @Override
    public void deleteOrderDetail(Long detailId) throws ResourceNotFoundException {
        OrderDetail detail = detailRepository.findById(detailId).orElseThrow(() -> new ResourceNotFoundException("order detail not found for this id: " + detailId));
        this.detailRepository.delete(detail);
    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetail detail) {
        return detailRepository.save(detail);
    }
}
