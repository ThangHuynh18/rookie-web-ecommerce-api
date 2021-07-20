package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.ErrorCode;
import com.rookie.webwatch.dto.OrderDTO;
import com.rookie.webwatch.dto.ProductDTO;
import com.rookie.webwatch.entity.*;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.OrderRepository;
import com.rookie.webwatch.repository.Productrepository;
import com.rookie.webwatch.repository.StatusRepository;
import com.rookie.webwatch.repository.UserRepository;
import com.rookie.webwatch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<OrderDTO> retrieveOrders() {
        List<Order> orders = orderRepository.findAll();

        return new OrderDTO().toListDto(orders);
    }

    @Override
    public Optional<OrderDTO> getOrder(Long orderId) throws ResourceNotFoundException {
        Order orders = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order not found for this id: "+orderId));
        return Optional.of(new OrderDTO().convertToDto(orders));
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) throws ResourceNotFoundException {

        User user = userRepository.findById(orderDTO.getUser_id()).orElseThrow(() ->
                new ResourceNotFoundException("user not found for this id: "+orderDTO.getUser_id()));

        Status status = statusRepository.findById(orderDTO.getStatus_id()).orElseThrow(() ->
                new ResourceNotFoundException("status not found for this id: "+orderDTO.getStatus_id()));

        Order order = new OrderDTO().convertToEti(orderDTO);
        order.setStatus(status);
        order.setUser(user);

        return new OrderDTO().convertToDto(orderRepository.save(order));
    }

    @Override
    public Boolean deleteOrder(Long orderId) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order not found for this id: " + orderId));
        this.orderRepository.delete(order);
        return true;
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) throws ResourceNotFoundException {
        Order orderExist = orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("order not found for this id: "+orderId));

        Status status = statusRepository.findById(orderDTO.getStatus_id()).orElseThrow(() ->
                new ResourceNotFoundException("status not found for this id: "+orderDTO.getStatus_id()));

        orderExist.setTotalQty(orderDTO.getTotalQty());
        orderExist.setTotalPrice(orderDTO.getTotalPrice());
        orderExist.setStatus(status);

        Order order = new Order();
        order = orderRepository.save(orderExist);
        return new OrderDTO().convertToDto(order);

    }

    @Override
    public List<OrderDTO> findOrderByUser(Long userId) throws ResourceNotFoundException {
        Optional<User> userExist = userRepository.findById(userId);
        if(!userExist.isPresent()){
            throw new ResourceNotFoundException(""+ ErrorCode.FIND_USER_ERROR);
        }
        User user = userExist.get();

        List<Order> list = null;
        list = orderRepository.getOrderByUser(user);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS = new OrderDTO().toListDto(list);
        return orderDTOS;
    }
}
