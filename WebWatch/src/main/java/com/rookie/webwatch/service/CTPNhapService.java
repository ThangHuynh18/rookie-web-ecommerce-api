package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.CTPDatDTO;
import com.rookie.webwatch.dto.CTPNhapDTO;
import com.rookie.webwatch.entity.CTPDHId;
import com.rookie.webwatch.entity.CTPNId;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CTPNhapService {
    public List<CTPNhapDTO> retrieveCTPNs();

    public Optional<CTPNhapDTO> getCTPN(CTPNId ctpnId) throws ResourceNotFoundException;

    public CTPNhapDTO saveCTPN(CTPNhapDTO nhapDTO) throws ResourceNotFoundException;

    public Boolean deleteCTPN(CTPNId ctpnId) throws ResourceNotFoundException;

    public CTPNhapDTO updateCTPN(CTPNId ctpnId, CTPNhapDTO nhapDTO) throws ResourceNotFoundException;
}
