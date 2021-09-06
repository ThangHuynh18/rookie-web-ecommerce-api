package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.PhieuDatDTO;
import com.rookie.webwatch.entity.PhieuDat;
import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.PhieuDatRepository;
import com.rookie.webwatch.repository.UserRepository;
import com.rookie.webwatch.service.PhieuDatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PhieuDatServiceImpl implements PhieuDatService {
    @Autowired
    private PhieuDatRepository phieuDatRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PhieuDatDTO> retrievePhieuDats() {
        List<PhieuDat> phieuDats = phieuDatRepository.findAll();

        return new PhieuDatDTO().toListDto(phieuDats);
    }

    @Override
    public Optional<PhieuDatDTO> getPhieuDat(Long datId) throws ResourceNotFoundException {
        PhieuDat phieuDat = phieuDatRepository.findById(datId).orElseThrow(() -> new ResourceNotFoundException("phieu dat not found for this id: "+datId));
        return Optional.of(new PhieuDatDTO().convertToDto(phieuDat));
    }

    @Override
    public PhieuDatDTO savePD(PhieuDatDTO phieuDatDTO) throws ResourceNotFoundException {
        User user = userRepository.findById(phieuDatDTO.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("user not found for this id: "+phieuDatDTO.getUserId()));
        PhieuDat phieuDat = new PhieuDatDTO().convertToEti(phieuDatDTO);
        phieuDat.setUser(user);

        return new PhieuDatDTO().convertToDto(phieuDatRepository.save(phieuDat));
    }

    @Override
    public Boolean deletePD(Long datId) throws ResourceNotFoundException {
        PhieuDat phieuDat = phieuDatRepository.findById(datId).orElseThrow(() -> new ResourceNotFoundException("phieu dat not found for this id: "+datId));
        this.phieuDatRepository.delete(phieuDat);
        return true;
    }

    @Override
    public PhieuDatDTO updatePD(Long datId) throws ResourceNotFoundException {
        PhieuDat phieuDatExist = phieuDatRepository.findById(datId).orElseThrow(() -> new ResourceNotFoundException("phieu dat not found for this id: "+datId));

        phieuDatExist.setCreateDate(LocalDate.now());

        PhieuDat phieuDat = new PhieuDat();
        phieuDat = phieuDatRepository.save(phieuDatExist);
        return new PhieuDatDTO().convertToDto(phieuDat);
    }
}
