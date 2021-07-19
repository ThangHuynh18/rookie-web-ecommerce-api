package com.rookie.webwatch.service.impl;

import com.rookie.webwatch.dto.BrandDTO;
import com.rookie.webwatch.dto.ErrorCode;
import com.rookie.webwatch.entity.Brand;

import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.BrandRepository;
import com.rookie.webwatch.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;


    @Override
    public List<BrandDTO> retrieveBrands() {
        List<Brand> brands = brandRepository.findAll();
        return new BrandDTO().toListDto(brands);
    }

    @Override
    public Optional<BrandDTO> getBrand(Long brandId) throws ResourceNotFoundException {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException(""+ ErrorCode.FIND_BRAND_ERROR));
        return Optional.of(new BrandDTO().convertToDto(brand));
    }

    @Override
    public BrandDTO saveBrand(BrandDTO brandDTO) {
        Brand brand = new BrandDTO().convertToEti(brandDTO);
        return new BrandDTO().convertToDto(brandRepository.save(brand));
    }

    @Override
    public Boolean deleteBrand(Long brandId) throws ResourceNotFoundException {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException(""+ ErrorCode.FIND_BRAND_ERROR));
        this.brandRepository.delete(brand);
        return true;
    }

    @Override
    public BrandDTO updateBrand(Long brandId, BrandDTO brandDTO) throws ResourceNotFoundException {
        Brand brandExist = brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException(""+ ErrorCode.FIND_BRAND_ERROR));

        brandExist.setBrandName(brandDTO.getBrandName());

        Brand brand = new Brand();
        brand = brandRepository.save(brandExist);
        return new BrandDTO().convertToDto(brand);
    }
}
