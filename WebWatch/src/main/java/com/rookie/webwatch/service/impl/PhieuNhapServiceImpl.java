package com.rookie.webwatch.service.impl;


import com.rookie.webwatch.dto.PhieuNhapDTO;
import com.rookie.webwatch.dto.PhieuNhapResponseDTO;
import com.rookie.webwatch.entity.*;
import com.rookie.webwatch.exception.ResourceNotFoundException;

import com.rookie.webwatch.repository.*;
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

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private Productrepository productrepository;


    @Override
    public List<PhieuNhapResponseDTO> retrievePhieuNhaps() {
        List<PhieuNhap> nhaps = nhapRepository.findAll();

        return new PhieuNhapResponseDTO().toListDto(nhaps);
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

        Status status = statusRepository.findById(nhapDTO.getStatusId()).orElseThrow(() ->
                new ResourceNotFoundException("status not found for this id: "+nhapDTO.getStatusId()));

        PhieuDat dat = datRepository.findById(nhapDTO.getDatId()).orElseThrow(() ->
                new ResourceNotFoundException("phieu dat not found for this id: "+nhapDTO.getDatId()));

        PhieuNhap nhap = new PhieuNhapDTO().convertToEti(nhapDTO);
        nhap.setUser(user);
        nhap.setPhieuDat(dat);
        nhap.setStatus(status);

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

    @Override
    public PhieuNhapDTO updateStatusPN(Long nhapId, String status) throws ResourceNotFoundException {
        PhieuNhap nhapExist = nhapRepository.findById(nhapId).orElseThrow(() ->
                new ResourceNotFoundException("phieu nhap not found for this id: "+nhapId));

        if(status.equals("waiting receipt")){
            Status stt = statusRepository.findAllByStatusName(status);
            nhapExist.setStatus(stt);
            Status sttDat = statusRepository.findAllByStatusName("complete");
            nhapExist.getPhieuDat().setStatus(sttDat);
            datRepository.save(nhapExist.getPhieuDat());
        } else if(status.equals("receipted")){
            Status stt = statusRepository.findAllByStatusName(status);
            nhapExist.setStatus(stt);
        }
        else if(status.equals("cancel")) {
            Status stt = statusRepository.findAllByStatusName(status);
            nhapExist.setStatus(stt);

            //Status sttDat = statusRepository.findAllByStatusName("no receipt");
            nhapExist.getPhieuDat().setStatus(stt);
            datRepository.save(nhapExist.getPhieuDat());

        } else if(status.equals("complete")) {
            Status stt = statusRepository.findAllByStatusName(status);
            nhapExist.setStatus(stt);
            nhapExist.getCtpNhaps().forEach(i -> {
                Product product = productrepository.getById(i.getCtpnId().getProductId());
                product.setProductQty(product.getProductQty() + i.getQtyNhap());
                productrepository.save(product);
            });
        }

        PhieuNhap nhap = new PhieuNhap();
        nhap = nhapRepository.save(nhapExist);

        return new PhieuNhapDTO().convertToDto(nhap);
    }
}
