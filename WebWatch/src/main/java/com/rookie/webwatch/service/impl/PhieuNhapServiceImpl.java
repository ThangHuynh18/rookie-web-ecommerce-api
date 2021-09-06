package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.PhieuNhapDTO;
import com.rookie.webwatch.entity.PhieuDat;
import com.rookie.webwatch.entity.PhieuNhap;
import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import com.rookie.webwatch.repository.PhieuDatRepository;
import com.rookie.webwatch.repository.PhieuNhapRepository;
import com.rookie.webwatch.repository.UserRepository;
import com.rookie.webwatch.service.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PhieuNhapServiceImpl implements PhieuNhapService {
    @Autowired
    private PhieuNhapRepository nhapRepository;

    @Autowired
    private PhieuDatRepository datRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PhieuNhapDTO> retrievePhieuNhaps() {
        List<PhieuNhap> nhaps = nhapRepository.findAll();

        return new PhieuNhapDTO().toListDto(nhaps);
    }

    @Override
    public Optional<PhieuNhapDTO> getPhieuNhap(Long nhapId) throws ResourceNotFoundException {
        PhieuNhap nhap = nhapRepository.findById(nhapId).orElseThrow(() -> new ResourceNotFoundException("phieu nhap not found for this id: "+nhapId));
        return Optional.of(new PhieuNhapDTO().convertToDto(nhap));
    }

    @Override
    public PhieuNhapDTO savePN(PhieuNhapDTO nhapDTO) throws ResourceNotFoundException {
        User user = userRepository.findById(nhapDTO.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("user not found for this id: "+nhapDTO.getUserId()));

        PhieuDat dat = datRepository.findById(nhapDTO.getDatId()).orElseThrow(() ->
                new ResourceNotFoundException("phieu dat not found for this id: "+nhapDTO.getDatId()));

        PhieuNhap nhap = new PhieuNhapDTO().convertToEti(nhapDTO);
        nhap.setUser(user);
        nhap.setPhieuDat(dat);

        return new PhieuNhapDTO().convertToDto(nhapRepository.save(nhap));
    }

    @Override
    public Boolean deletePN(Long nhapId) throws ResourceNotFoundException {
        PhieuNhap nhap = nhapRepository.findById(nhapId).orElseThrow(() -> new ResourceNotFoundException("phieu dat not found for this id: "+nhapId));
        this.nhapRepository.delete(nhap);
        return true;
    }

    @Override
    public PhieuNhapDTO updatePN(Long nhapId) throws ResourceNotFoundException {
        PhieuNhap nhapExist = nhapRepository.findById(nhapId).orElseThrow(() -> new ResourceNotFoundException("phieu dat not found for this id: "+nhapId));

        nhapExist.setCreateDate(LocalDate.now());

        PhieuNhap nhap = new PhieuNhap();
        nhap = nhapRepository.save(nhapExist);
        return new PhieuNhapDTO().convertToDto(nhap);
    }
}
