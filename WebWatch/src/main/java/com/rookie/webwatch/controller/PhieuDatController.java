package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.*;
import com.rookie.webwatch.exception.*;
import com.rookie.webwatch.service.PhieuDatService;
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
@RequestMapping("/api/phieudats")
public class PhieuDatController {
    @Autowired
    private PhieuDatService phieuDatService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAll() throws GetDataFail {
        ResponseDTO response = new ResponseDTO();
        List<ResponseDTO> responseDTO = new ArrayList<>();
        try {
            List<PhieuDatDTO> phieuDatDTOS = phieuDatService.retrievePhieuDats();
            List list = Collections.synchronizedList(new ArrayList(phieuDatDTOS));

            if (responseDTO.addAll(list) == true) {
                response.setData(phieuDatDTOS);
            }
            response.setSuccessCode(SuccessCode.GET_ALL_PHIEU_DAT_SUCCESS);
        } catch (Exception e){
            throw new GetDataFail(""+ ErrorCode.GET_PHIEU_DAT_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{dat_id}")
    public ResponseEntity<ResponseDTO> findPD(@PathVariable("dat_id") Long datId) throws ResourceNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<PhieuDatDTO> phieuDatDTO = phieuDatService.getPhieuDat(datId);

            responseDTO.setData(phieuDatDTO);
            responseDTO.setSuccessCode(SuccessCode.FIND_PHIEU_DAT_SUCCESS);
        } catch (Exception e){
            throw new ResourceNotFoundException(""+ErrorCode.FIND_PHIEU_DAT_ERROR);
        }
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/phieu")
    public ResponseEntity<ResponseDTO> createPD(@Valid @RequestBody PhieuDatDTO phieuDatDTO) throws AddDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            PhieuDatDTO dto = phieuDatService.savePD(phieuDatDTO);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.ADD_PHIEU_DAT_SUCCESS);
        } catch (Exception e){
            throw new AddDataFail(""+ErrorCode.ADD_PHIEU_DAT_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
    //
////    //update
    @PutMapping("/phieu/{dat_id}")
    public ResponseEntity<ResponseDTO> updatePD(@PathVariable(value = "dat_id") Long datId) throws UpdateDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            PhieuDatDTO updatePD = phieuDatService.updatePD(datId);

            responseDTO.setData(updatePD);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_PHIEU_DAT_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFail(""+ErrorCode.UPDATE_PHIEU_DAT_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }

    ////    //delete
    @DeleteMapping("/phieu/{dat_id}")
    public ResponseEntity<ResponseDTO> deletePD(@PathVariable(value = "dat_id") Long datId) throws DeleteDataFail {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Boolean isDel = phieuDatService.deletePD(datId);
            responseDTO.setData(isDel);
            responseDTO.setSuccessCode(SuccessCode.DELETE_PHIEU_DAT_SUCCESS);
        } catch (Exception e){
            throw new DeleteDataFail(""+ErrorCode.DELETE_PHIEU_DAT_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
}
