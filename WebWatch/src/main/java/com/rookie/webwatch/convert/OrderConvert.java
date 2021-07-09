package com.rookie.webwatch.convert;

import com.rookie.webwatch.dto.OrderDTO;
import com.rookie.webwatch.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderConvert {
    @Autowired
    private ModelMapper modelMapper;

    public OrderDTO convertToDto(Optional<Order> order) {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setOrder_id(order.get().getOrder_id());
        orderDTO.setTotalPrice(order.get().getTotalPrice());
        orderDTO.setTotalQty(order.get().getTotalQty());
        orderDTO.setStatus_id(order.get().getStatus().getStatus_id());
        orderDTO.setOrder_id(order.get().getOrder_id());
        System.out.println("______________________-"+order.get().getStatus().getStatus_id());
        return orderDTO;
    }


    public OrderDTO convertToDtoForUpdate(Order order) {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setOrder_id(order.getOrder_id());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setTotalQty(order.getTotalQty());

        return orderDTO;
    }

    public Order convertToEntity(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);

        return order;
    }
}
