package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.*;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    private User user = new User(16,"user21", "user@gmail.com","123");
    private Status status = new Status(1,1,"shipping");
    private Order order = new Order(21, 2021, user, status);

    @Test
    public void getOrder(){
        when(orderRepository.findAll())
                .thenReturn(Stream.of(new Order(22, 2022, user, status),
                        new Order(23, 2023, user, status))
                        .collect(Collectors.toList()));
        Assert.assertEquals(2,orderService.retrieveOrders().size());
    }

    @Test
    public void addOrder(){
        when(orderRepository.save(any())).thenReturn(order);

        Assert.assertEquals(21, order.getTotalQty());
    }

    @Test
    public void updateOrder(){
        Order order = new Order(21, 2021, user, status);
        when(orderRepository.findById(order.getOrder_id())).thenReturn(Optional.of(order));

        when(orderRepository.save(any())).thenReturn(order);

        Assert.assertEquals(2021,0, order.getTotalPrice());
    }

    @Test
    public void deleteOrder() throws ResourceNotFoundException {
        Order order = new Order(21, 2021, user, status);
        when(orderRepository.findById(order.getOrder_id())).thenReturn(Optional.of(order));

        orderService.deleteOrder(order.getOrder_id());

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    public void loadOrderById(){
        // Given
        Order order = new Order();
        User user = new User(16,"user21", "user@gmail.com","123");
        Status status = new Status(1,1,"shipping");
        order.setOrder_id(20L);
        order.setTotalQty(20);
        order.setTotalPrice(2020);
        order.setUser(user);
        order.setStatus(status);

        when(orderRepository.findById(order.getOrder_id())).thenReturn(java.util.Optional.of(order));

        Assert.assertEquals(2020,0,order.getTotalPrice());

    }
}
