package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.CTPDatDTO;
import com.rookie.webwatch.entity.CTPDHId;
import com.rookie.webwatch.entity.CTPDat;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.CTPDatRepository;
import com.rookie.webwatch.service.CTPDatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CTPDatServiceImpl implements CTPDatService {
    @Autowired
    private CTPDatRepository ctpDatRepository;

    @Override
    public List<CTPDatDTO> retrieveCTPDs() {
        List<CTPDat> phieuDats = ctpDatRepository.findAll();

        return new CTPDatDTO().toListDto(phieuDats);
    }

    @Override
    public Optional<CTPDatDTO> getCTPD(CTPDHId ctpdhId) throws ResourceNotFoundException {
        CTPDat phieuDat = ctpDatRepository.findById(ctpdhId).orElseThrow(() -> new ResourceNotFoundException("phieu dat not found for this id"+ctpdhId));
        return Optional.of(new CTPDatDTO().convertToDto(phieuDat));
    }

    @Override
    public CTPDatDTO saveCTPD(CTPDatDTO ctpDatDTO) throws ResourceNotFoundException {
        CTPDat phieuDat = new CTPDatDTO().convertToEti(ctpDatDTO);

        return new CTPDatDTO().convertToDto(ctpDatRepository.save(phieuDat));
    }

    @Override
    public Boolean deleteCTPD(CTPDHId ctpdhId) throws ResourceNotFoundException {
        CTPDat ctpDat = ctpDatRepository.findById(ctpdhId).orElseThrow(() -> new ResourceNotFoundException("phieu dat not found for this id"));
        this.ctpDatRepository.delete(ctpDat);
        return true;
    }

    @Override
    public CTPDatDTO updateCTPD(CTPDHId ctpdhId, CTPDatDTO ctpDatDTO) throws ResourceNotFoundException {
        CTPDat ctpdExist = ctpDatRepository.findById(ctpdhId).orElseThrow(() -> new ResourceNotFoundException("phieu dat not found for this id"));
        ctpdExist.setQtyDat(ctpDatDTO.getQtyDat());
        ctpdExist.setPriceDat(ctpDatDTO.getPriceDat());

        CTPDat phieuDat = new CTPDat();
        phieuDat = ctpDatRepository.save(ctpdExist);
        return new CTPDatDTO().convertToDto(phieuDat);
    }
}
