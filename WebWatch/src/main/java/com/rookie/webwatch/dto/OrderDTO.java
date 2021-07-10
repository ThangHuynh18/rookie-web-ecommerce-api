package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Order;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private long order_id;

    @NotNull
    private int totalQty;

    @NotNull
    private float totalPrice;
    private long user_id;
    private long status_id;


    public OrderDTO convertToDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder_id(order.getOrder_id());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setTotalQty(order.getTotalQty());
        orderDTO.setStatus_id(order.getStatus().getStatus_id());
        orderDTO.setUser_id(order.getUser().getUser_id());

        return orderDTO;
    }

    public Order convertToEti(OrderDTO orderDTO) {
        Order order = new Order();

        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setTotalQty(orderDTO.getTotalQty());

        return order;
    }


    public List<OrderDTO> toListDto(List<Order> listEntity) {
        List<OrderDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }

    public OrderDTO() {

    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }
}
