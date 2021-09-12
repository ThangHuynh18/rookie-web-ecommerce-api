package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.CTPDatDTO;
import com.rookie.webwatch.dto.CTPDatResponseDTO;
import com.rookie.webwatch.entity.CTPDHId;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CTPDatService {
    public List<CTPDatDTO> retrieveCTPDs();

    public Optional<CTPDatDTO> getCTPD(CTPDHId ctpdhId) throws ResourceNotFoundException;

    public CTPDatDTO saveCTPD(CTPDatDTO ctpDatDTO) throws ResourceNotFoundException;

    public Boolean deleteCTPD(CTPDHId ctpdhId) throws ResourceNotFoundException;

    public CTPDatDTO updateCTPD(CTPDHId ctpdhId, CTPDatDTO ctpDatDTO) throws ResourceNotFoundException;

    public List<CTPDatResponseDTO> findCTByPD(long datId) throws ResourceNotFoundException;
}
