package com.rookie.webwatch.service;

import com.rookie.webwatch.dto.PhieuDatDTO;
import com.rookie.webwatch.dto.PhieuNhapDTO;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PhieuNhapService {
    public List<PhieuNhapDTO> retrievePhieuNhaps();

    public Optional<PhieuNhapDTO> getPhieuNhap(Long nhapId) throws ResourceNotFoundException;

    public PhieuNhapDTO savePN(PhieuNhapDTO nhapDTO) throws ResourceNotFoundException;

    public Boolean deletePN(Long nhapId) throws ResourceNotFoundException;

    public PhieuNhapDTO updatePN(Long nhapId) throws ResourceNotFoundException;
}
