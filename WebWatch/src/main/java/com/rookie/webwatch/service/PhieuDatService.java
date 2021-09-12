package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.OrderDTO;
import com.rookie.webwatch.dto.PhieuDatDTO;
import com.rookie.webwatch.dto.PhieuDatResponseDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PhieuDatService {
    public List<PhieuDatResponseDTO> retrievePhieuDats();

    public PhieuDatResponseDTO getPhieuDat(Long datId) throws ResourceNotFoundException;

    public PhieuDatDTO savePD(PhieuDatDTO phieuDatDTO) throws ResourceNotFoundException;

    public Boolean deletePD(Long datId) throws ResourceNotFoundException;

    public PhieuDatDTO updatePD(Long datId) throws ResourceNotFoundException;

    public PhieuDatDTO updateStatusPD(Long datId, String status) throws ResourceNotFoundException;
}
