package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.CTPDatDTO;
import com.rookie.webwatch.dto.CTPNhapDTO;
import com.rookie.webwatch.entity.CTPDat;
import com.rookie.webwatch.entity.CTPNId;
import com.rookie.webwatch.entity.CTPNhap;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.CTPNhapRepository;
import com.rookie.webwatch.service.CTPNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CTPNhapServiceImpl implements CTPNhapService {
    @Autowired
    private CTPNhapRepository nhapRepository;

    @Override
    public List<CTPNhapDTO> retrieveCTPNs() {
        List<CTPNhap> nhaps = nhapRepository.findAll();

        return new CTPNhapDTO().toListDto(nhaps);
    }

    @Override
    public Optional<CTPNhapDTO> getCTPN(CTPNId ctpnId) throws ResourceNotFoundException {
        CTPNhap nhap = nhapRepository.findById(ctpnId).orElseThrow(() -> new ResourceNotFoundException("phieu nhap not found for this id"+ctpnId));
        return Optional.of(new CTPNhapDTO().convertToDto(nhap));
    }

    @Override
    public CTPNhapDTO saveCTPN(CTPNhapDTO nhapDTO) throws ResourceNotFoundException {
        CTPNhap nhap = new CTPNhapDTO().convertToEti(nhapDTO);

        return new CTPNhapDTO().convertToDto(nhapRepository.save(nhap));
    }

    @Override
    public Boolean deleteCTPN(CTPNId ctpnId) throws ResourceNotFoundException {
        CTPNhap nhap = nhapRepository.findById(ctpnId).orElseThrow(() -> new ResourceNotFoundException("phieu nhap not found for this id"));
        this.nhapRepository.delete(nhap);
        return true;
    }

    @Override
    public CTPNhapDTO updateCTPN(CTPNId ctpnId, CTPNhapDTO nhapDTO) throws ResourceNotFoundException {
        CTPNhap nhapExist = nhapRepository.findById(ctpnId).orElseThrow(() -> new ResourceNotFoundException("phieu nhap not found for this id"));
        nhapExist.setQtyNhap(nhapDTO.getQtyNhap());
        nhapExist.setPriceNhap(nhapDTO.getPriceNhap());

        CTPNhap phieuNhap = new CTPNhap();
        phieuNhap = nhapRepository.save(nhapExist);
        return new CTPNhapDTO().convertToDto(phieuNhap);
    }
}
