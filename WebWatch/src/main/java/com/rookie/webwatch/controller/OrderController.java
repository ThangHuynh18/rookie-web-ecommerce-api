package com.rookie.webwatch.controller;

import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<Order> getAllOrder(){
        List<Order> orders = orderService.retrieveOrders();
        return orders;
    }

    @GetMapping("/{order_id}")
    public Optional<Order> findOrder(@PathVariable("order_id") Long orderId) throws ResourceNotFoundException {
        Optional<Order> order = Optional.ofNullable(orderService.getOrder(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("order not found for this id: " + orderId)));

        return orderService.getOrder(orderId);
    }

    //save employee
    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }
    //
//    //update
    @PutMapping("/order/{order_id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "order_id") Long orderId,
                                                 @RequestBody Order orderDetail) throws ResourceNotFoundException{
        Order order = orderService.getOrder(orderId).orElseThrow(() -> new ResourceNotFoundException("order not found for this id: " +orderId));

        order.setTotalQty(orderDetail.getTotalQty());
        order.setTotalPrice(orderDetail.getTotalPrice());

        order.setStatus(orderDetail.getStatus());
//        order.setUser(orderDetail.getUser());
//        order.setOrderDetails(orderDetail.getOrderDetails());

        return ResponseEntity.ok(orderService.updateOrder(order));
    }
    //
//    //delete
    @DeleteMapping("/order/{order_id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "order_id") Long orderId)
            throws ResourceNotFoundException {
        orderService.deleteOrder(orderId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }
}
