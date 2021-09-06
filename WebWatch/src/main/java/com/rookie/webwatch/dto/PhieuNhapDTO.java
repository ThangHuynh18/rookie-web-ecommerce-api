package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.PhieuNhap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PhieuNhapDTO {
    private long nhapId;
    private LocalDate createDate;
    private long userId;
    private long datId;

    public PhieuNhapDTO convertToDto(PhieuNhap phieuNhap) {
        PhieuNhapDTO nhapDTO = new PhieuNhapDTO();
        nhapDTO.setNhapId(phieuNhap.getNhapId());
        nhapDTO.setCreateDate(phieuNhap.getCreateDate());
        nhapDTO.setUserId(phieuNhap.getUser().getUser_id());
        nhapDTO.setDatId(phieuNhap.getPhieuDat().getDatId());

        return nhapDTO;
    }

    public PhieuNhap convertToEti(PhieuNhapDTO nhapDTO) {
        PhieuNhap nhap = new PhieuNhap();

        nhap.setCreateDate(LocalDate.now());

        return nhap;
    }


    public List<PhieuNhapDTO> toListDto(List<PhieuNhap> listEntity) {
        List<PhieuNhapDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }
}
