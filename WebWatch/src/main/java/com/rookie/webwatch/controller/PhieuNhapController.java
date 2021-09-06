package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.*;
import com.rookie.webwatch.exception.*;
import com.rookie.webwatch.service.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/phieunhaps")
public class PhieuNhapController {
    @Autowired
    private PhieuNhapService nhapService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAll() throws GetDataFail {
        ResponseDTO response = new ResponseDTO();
        List<ResponseDTO> responseDTO = new ArrayList<>();
        try {
            List<PhieuNhapDTO> nhapDTOS = nhapService.retrievePhieuNhaps();
            List list = Collections.synchronizedList(new ArrayList(nhapDTOS));

            if (responseDTO.addAll(list) == true) {
                response.setData(nhapDTOS);
            }
            response.setSuccessCode(SuccessCode.GET_ALL_PHIEU_NHAP_SUCCESS);
        } catch (Exception e){
            throw new GetDataFail(""+ ErrorCode.GET_PHIEU_NHAP_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{nhap_id}")
    public ResponseEntity<ResponseDTO> findPN(@PathVariable("nhap_id") Long nhapId) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<PhieuNhapDTO> nhapDTO = nhapService.getPhieuNhap(nhapId);

            responseDTO.setData(nhapDTO);
            responseDTO.setSuccessCode(SuccessCode.FIND_PHIEU_NHAP_SUCCESS);
        } catch (Exception e){
            throw new ResourceNotFoundException(""+ErrorCode.FIND_PHIEU_NHAP_ERROR);
        }
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/phieu")
    public ResponseEntity<ResponseDTO> createPN(@Valid @RequestBody PhieuNhapDTO nhapDTO) throws AddDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            PhieuNhapDTO dto = nhapService.savePN(nhapDTO);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.ADD_PHIEU_NHAP_SUCCESS);
        } catch (Exception e){
            throw new AddDataFail(""+ErrorCode.ADD_PHIEU_NHAP_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
    //
////    //update
    @PutMapping("/phieu/{nhap_id}")
    public ResponseEntity<ResponseDTO> updatePD(@PathVariable(value = "nhap_id") Long nhapId) throws UpdateDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            PhieuNhapDTO update = nhapService.updatePN(nhapId);

            responseDTO.setData(update);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_PHIEU_NHAP_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFail(""+ErrorCode.UPDATE_PHIEU_NHAP_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

    ////    //delete
    @DeleteMapping("/phieu/{nhap_id}")
    public ResponseEntity<ResponseDTO> deletePD(@PathVariable(value = "nhap_id") Long nhapId) throws DeleteDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Boolean isDel = nhapService.deletePN(nhapId);
            responseDTO.setData(isDel);
            responseDTO.setSuccessCode(SuccessCode.DELETE_PHIEU_NHAP_SUCCESS);
        } catch (Exception e){
            throw new DeleteDataFail(""+ErrorCode.DELETE_PHIEU_NHAP_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
}
