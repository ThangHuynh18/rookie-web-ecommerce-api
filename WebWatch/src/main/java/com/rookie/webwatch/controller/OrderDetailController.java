package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.OrderDetailDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/odetails")
public class OrderDetailController {
    @Autowired
    private OrderDetailService detailService;

    @GetMapping("")
    public List<OrderDetailDTO> getAllOrderDetail(){
        List<OrderDetailDTO> details = detailService.retrieveOrderDetails();
        return details;
    }

    @GetMapping("/{detail_id}")
    public ResponseEntity<OrderDetailDTO> findOrderDetail(@PathVariable("detail_id") Long detailId) throws ResourceNotFoundException {
        OrderDetailDTO detailDTO = detailService.getOrderDetail(detailId);

        return ResponseEntity.ok(detailDTO);
    }

    //save
    @PostMapping("/odetail")
    public ResponseEntity<OrderDetailDTO> createOrderDetail(@Valid @RequestBody OrderDetailDTO detailDTO) throws ResourceNotFoundException {
        OrderDetailDTO dto = detailService.saveOrderDetail(detailDTO);
        return ResponseEntity.ok(dto);
    }
    //
//    //update
    @PutMapping("/odetail/{detail_id}")
    public ResponseEntity<OrderDetailDTO> updateOrderDetail(@PathVariable(value = "detail_id") Long detailId,
                                             @Valid @RequestBody OrderDetailDTO detailDTO) throws ResourceNotFoundException{
        OrderDetailDTO updateDetail = detailService.updateOrderDetail(detailId, detailDTO);

        return new ResponseEntity<>(updateDetail, HttpStatus.OK);
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
