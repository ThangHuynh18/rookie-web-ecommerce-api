package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.*;

import com.rookie.webwatch.exception.*;
import com.rookie.webwatch.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/odetails")
public class OrderDetailController {
    @Autowired
    private OrderDetailService detailService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAllOrderDetail() throws GetDataFail {
        ResponseDTO response = new ResponseDTO();
        List<ResponseDTO> responseDTO = new ArrayList<>();
        try {
            List<OrderDetailDTO> details = detailService.retrieveOrderDetails();
            List list = Collections.synchronizedList(new ArrayList(details));

            if (responseDTO.addAll(list) == true) {
                response.setData(details);
            }
            response.setSuccessCode(SuccessCode.GET_ALL_ORDER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new GetDataFail(""+ ErrorCode.GET_ORDER_DETAIL_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{detail_id}")
    public ResponseEntity<ResponseDTO> findOrderDetail(@PathVariable("detail_id") Long detailId) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<OrderDetailDTO> detailDTO = detailService.getOrderDetail(detailId);

            responseDTO.setData(detailDTO);
            responseDTO.setSuccessCode(SuccessCode.FIND_ORDER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new ResourceNotFoundException(""+ErrorCode.FIND_ORDER_DETAIL_ERROR);
        }
        return ResponseEntity.ok(responseDTO);
    }

    //save
    @PostMapping("/odetail")
    public ResponseEntity<ResponseDTO> createOrderDetail(@Valid @RequestBody OrderDetailDTO detailDTO) throws AddDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            OrderDetailDTO dto = detailService.saveOrderDetail(detailDTO);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.ADD_ORDER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new AddDataFail(""+ErrorCode.ADD_ORDER_DETAIL_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
    //
//    //update
    @PutMapping("/odetail/{detail_id}")
    public ResponseEntity<ResponseDTO> updateOrderDetail(@PathVariable(value = "detail_id") Long detailId,
                                             @Valid @RequestBody OrderDetailDTO detailDTO) throws UpdateDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            OrderDetailDTO updateDetail = detailService.updateOrderDetail(detailId, detailDTO);

            responseDTO.setData(updateDetail);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_ORDER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFail(""+ErrorCode.UPDATE_ORDER_DETAIL_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
    //
//    //delete
    @DeleteMapping("/odetail/{detail_id}")
    public ResponseEntity<ResponseDTO> deleteOrderDetail(@PathVariable(value = "detail_id") Long detailId) throws DeleteDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Boolean isDel = detailService.deleteOrderDetail(detailId);
            responseDTO.setData(isDel);
            responseDTO.setSuccessCode(SuccessCode.DELETE_ORDER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new DeleteDataFail(""+ErrorCode.DELETE_ORDER_DETAIL_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/order/{order_id}")
    public ResponseEntity<ResponseDTO> findDetailByOrder(@PathVariable("order_id") @NotBlank Long orderId) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<OrderDetailResponseDTO> detailDTOS = detailService.findDetailByOrder(orderId);
        responseDTO.setData(detailDTOS);
        responseDTO.setSuccessCode(SuccessCode.FIND_ORDER_DETAIL_SUCCESS);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/odetail/qty/{product_id}")
    public ResponseEntity<ResponseDTO> restoreQty(@PathVariable(value = "product_id") Long productId) throws UpdateDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            OrderDetailDTO updateDetail = detailService.restoreQty(productId);

            responseDTO.setData(updateDetail);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_ORDER_DETAIL_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFail(""+ErrorCode.UPDATE_ORDER_DETAIL_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
}
