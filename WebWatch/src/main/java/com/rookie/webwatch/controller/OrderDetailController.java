package com.rookie.webwatch.controller;

import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.entity.OrderDetail;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/odetails")
public class OrderDetailController {
    @Autowired
    private OrderDetailService detailService;

    @GetMapping("")
    public List<OrderDetail> getAllOrderDetail(){
        List<OrderDetail> details = detailService.retrieveOrderDetails();
        return details;
    }

    @GetMapping("/{detail_id}")
    public Optional<OrderDetail> findOrderDetail(@PathVariable("detail_id") Long detailId) throws ResourceNotFoundException {
        Optional<OrderDetail> order = Optional.ofNullable(detailService.getOrderDetail(detailId)
                .orElseThrow(() -> new ResourceNotFoundException("order detail not found for this id: " + detailId)));

        return detailService.getOrderDetail(detailId);
    }

    //save employee
    @PostMapping("/odetail")
    public OrderDetail createOrderDetail(@RequestBody OrderDetail detail){
        return detailService.saveOrderDetail(detail);
    }
    //
//    //update
    @PutMapping("/odetail/{detail_id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable(value = "detail_id") Long detailId,
                                             @RequestBody OrderDetail orderDetail) throws ResourceNotFoundException{
        OrderDetail detail = detailService.getOrderDetail(detailId).orElseThrow(() -> new ResourceNotFoundException("order detail not found for this id: " +detailId));

        detail.setDetailQty(orderDetail.getDetailQty());
        detail.setDetailPrice(orderDetail.getDetailPrice());

//        detail.setOrder(orderDetail.getOrder());
//        detail.setProduct(orderDetail.getProduct());

        return ResponseEntity.ok(detailService.updateOrderDetail(detail));
    }
    //
//    //delete
    @DeleteMapping("/odetail/{detail_id}")
    public Map<String, Boolean> deleteOrderDetail(@PathVariable(value = "detail_id") Long detailId)
            throws ResourceNotFoundException {
        detailService.deleteOrderDetail(detailId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }
}
