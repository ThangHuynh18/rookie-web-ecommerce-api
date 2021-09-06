package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.PhieuDatDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PhieuDatService {
    public List<PhieuDatDTO> retrievePhieuDats();

    public Optional<PhieuDatDTO> getPhieuDat(Long datId) throws ResourceNotFoundException;

    public PhieuDatDTO savePD(PhieuDatDTO phieuDatDTO) throws ResourceNotFoundException;

    public Boolean deletePD(Long datId) throws ResourceNotFoundException;

    public PhieuDatDTO updatePD(Long datId) throws ResourceNotFoundException;
}
