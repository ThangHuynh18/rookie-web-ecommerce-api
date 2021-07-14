package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void createOrder(){
        Order order = new Order();
        User user = new User(16,"user21", "user@gmail.com","123");
        Status status = new Status(1,1,"shipping");
        order.setOrder_id(20L);
        order.setTotalQty(20);
        order.setTotalPrice(2020);
        order.setUser(user);
        order.setStatus(status);

        Assert.assertNotNull(orderRepository.save(order));
    }

    @Test
    public void GivenGetAllOrderShouldReturnListOfAllOrders(){
        User user = new User(16,"user21", "user@gmail.com","123");
        Status status = new Status(1,1,"shipping");
        Order order1 = new Order(21, 2021, user, status);
        Order order2 = new Order(22, 2022, user, status);

        orderRepository.save(order1);
        orderRepository.save(order2);
        List<Order> orders = orderRepository.findAll();
        assertEquals(2021.0, orders.get(7).getTotalPrice());
        assertEquals(22, orders.get(8).getTotalQty());
    }

    @Test
    public void givenIdThenShouldReturnOrderOfThatId() {
        User user = new User(16,"user21", "user@gmail.com","123");
        Status status = new Status(1,1,"shipping");
        Order order1 = new Order(23, 2023, user, status);
        Order order = orderRepository.save(order1);
        Optional<Order> optional =  orderRepository.findById(order.getOrder_id());
        assertEquals(order.getOrder_id(), optional.get().getOrder_id());
        assertEquals(order.getTotalPrice(), optional.get().getTotalPrice());
    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheOrder() {
        User user = new User(16,"user21", "user@gmail.com","123");
        Status status = new Status(1,1,"shipping");
        Order order1 = new Order(23, 2023, user, status);
        orderRepository.save(order1);
        orderRepository.deleteById(order1.getOrder_id());
        Optional optional = orderRepository.findById(order1.getOrder_id());
        assertEquals(Optional.empty(), optional);
    }
}
