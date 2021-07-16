package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.OrderDetailDTO;

import com.rookie.webwatch.entity.*;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.OrderDetailRepository;
import com.rookie.webwatch.repository.OrderRepository;
import com.rookie.webwatch.repository.Productrepository;
import com.rookie.webwatch.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Productrepository productrepository;

    @Override
    public List<OrderDetailDTO> retrieveOrderDetails() {
        List<OrderDetail> details = detailRepository.findAll();
        return new OrderDetailDTO().toListDto(details);
    }

    @Override
    public Optional<OrderDetailDTO> getOrderDetail(Long detailId) throws ResourceNotFoundException {
        OrderDetail detail = detailRepository.findById(detailId).orElseThrow(() -> new ResourceNotFoundException("detail not found for this id: "+detailId));
        return Optional.of(new OrderDetailDTO().convertToDto(detail));
    }

    @Override
    public OrderDetailDTO saveOrderDetail(OrderDetailDTO detailDTO) throws ResourceNotFoundException {
        Order order = orderRepository.findById(detailDTO.getOrder_id()).orElseThrow(() ->
                new ResourceNotFoundException("order not found for this id: "+detailDTO.getOrder_id()));

        Product product = productrepository.findById(detailDTO.getProduct_id()).orElseThrow(() ->
                new ResourceNotFoundException("product not found for this id: "+detailDTO.getProduct_id()));

        OrderDetail detail = new OrderDetailDTO().convertToEti(detailDTO);
        detail.setOrder(order);
        detail.setProduct(product);

        return new OrderDetailDTO().convertToDto(detailRepository.save(detail));
    }

    @Override
    public Boolean deleteOrderDetail(Long detailId) throws ResourceNotFoundException {
        OrderDetail detail = detailRepository.findById(detailId).orElseThrow(() -> new ResourceNotFoundException("order detail not found for this id: " + detailId));
        this.detailRepository.delete(detail);
        return true;
    }

    @Override
    public OrderDetailDTO updateOrderDetail(Long id, OrderDetailDTO detailDTO) throws ResourceNotFoundException {
        OrderDetail detailExist = detailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("order detail not found for this id: "+id));

        detailExist.setDetailQty(detailDTO.getDetailQty());
        detailExist.setDetailPrice(detailDTO.getDetailPrice());

        OrderDetail detail = new OrderDetail();
        detail = detailRepository.save(detailExist);
        return new OrderDetailDTO().convertToDto(detail);
    }
}
