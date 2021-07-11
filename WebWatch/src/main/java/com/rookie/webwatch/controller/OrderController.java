package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.OrderDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<OrderDTO> getAllOrder(){
        List<OrderDTO> orderDTOS = orderService.retrieveOrders();

        return orderDTOS;
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderDTO> findOrder(@PathVariable("order_id") Long orderId) throws ResourceNotFoundException {
        OrderDTO orderDTO = orderService.getOrder(orderId);

        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) throws ResourceNotFoundException {
        OrderDTO dto = orderService.saveOrder(orderDTO);
        return ResponseEntity.ok(dto);
    }
//
////    //update
    @PutMapping("/order/{order_id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable(value = "order_id") Long orderId,
                                                 @Valid @RequestBody OrderDTO orderDTO) throws ResourceNotFoundException{
        OrderDTO updateOrder = orderService.updateOrder(orderId, orderDTO);

        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

////    //delete
    @DeleteMapping("/order/{order_id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "order_id") Long orderId)
            throws ResourceNotFoundException {
        orderService.deleteOrder(orderId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }
}
