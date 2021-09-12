package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.PhieuDatDTO;
import com.rookie.webwatch.dto.PhieuNhapDTO;
import com.rookie.webwatch.dto.PhieuNhapResponseDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PhieuNhapService {
    public List<PhieuNhapResponseDTO> retrievePhieuNhaps();

    public PhieuNhapResponseDTO getPhieuNhap(Long nhapId) throws ResourceNotFoundException;

    public PhieuNhapDTO savePN(PhieuNhapDTO nhapDTO) throws ResourceNotFoundException;

    public Boolean deletePN(Long nhapId) throws ResourceNotFoundException;

    public PhieuNhapDTO updatePN(Long nhapId) throws ResourceNotFoundException;

    public PhieuNhapDTO updateStatusPN(Long nhapId, String status) throws ResourceNotFoundException;
}
