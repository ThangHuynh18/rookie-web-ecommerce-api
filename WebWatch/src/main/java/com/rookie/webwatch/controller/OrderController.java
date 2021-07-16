package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.ErrorCode;
import com.rookie.webwatch.dto.OrderDTO;

import com.rookie.webwatch.dto.ResponseDTO;
import com.rookie.webwatch.dto.SuccessCode;
import com.rookie.webwatch.exception.*;
import com.rookie.webwatch.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAllOrder() throws GetDataFail {
        ResponseDTO response = new ResponseDTO();
        List<ResponseDTO> responseDTO = new ArrayList<>();
        try {
            List<OrderDTO> orderDTOS = orderService.retrieveOrders();
            List list = Collections.synchronizedList(new ArrayList(orderDTOS));

            if (responseDTO.addAll(list) == true) {
                response.setData(orderDTOS);
            }
            response.setSuccessCode(SuccessCode.GET_ALL_ORDER_SUCCESS);
        } catch (Exception e){
            throw new GetDataFail(""+ ErrorCode.GET_ORDER_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<ResponseDTO> findOrder(@PathVariable("order_id") Long orderId) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<OrderDTO> orderDTO = orderService.getOrder(orderId);

            responseDTO.setData(orderDTO);
            responseDTO.setSuccessCode(SuccessCode.FIND_ORDER_SUCCESS);
        } catch (Exception e){
            throw new ResourceNotFoundException(""+ErrorCode.FIND_ORDER_ERROR);
        }
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/order")
    public ResponseEntity<ResponseDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) throws AddDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            OrderDTO dto = orderService.saveOrder(orderDTO);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.ADD_ORDER_SUCCESS);
        } catch (Exception e){
            throw new AddDataFail(""+ErrorCode.ADD_ORDER_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
//
////    //update
    @PutMapping("/order/{order_id}")
    public ResponseEntity<ResponseDTO> updateOrder(@PathVariable(value = "order_id") Long orderId,
                                                 @Valid @RequestBody OrderDTO orderDTO) throws UpdateDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            OrderDTO updateOrder = orderService.updateOrder(orderId, orderDTO);

            responseDTO.setData(updateOrder);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_ORDER_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFail(""+ErrorCode.UPDATE_ORDER_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

////    //delete
    @DeleteMapping("/order/{order_id}")
    public ResponseEntity<ResponseDTO> deleteOrder(@PathVariable(value = "order_id") Long orderId) throws DeleteDataFail {
            ResponseDTO responseDTO = new ResponseDTO();
            try {
            Boolean isDel = orderService.deleteOrder(orderId);
                responseDTO.setData(isDel);
                responseDTO.setSuccessCode(SuccessCode.DELETE_ORDER_SUCCESS);
            } catch (Exception e){
                throw new DeleteDataFail(""+ErrorCode.DELETE_ORDER_ERROR);
            }

        return ResponseEntity.ok(responseDTO);
    }
}
