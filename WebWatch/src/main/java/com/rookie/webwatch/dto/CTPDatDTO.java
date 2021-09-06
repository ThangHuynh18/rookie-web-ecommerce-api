package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.CTPDHId;
import com.rookie.webwatch.entity.CTPDat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CTPDatDTO {
    private CTPDHId id;
    @Min(value = 1)
    private int qtyDat;
    @Min(value = 1)
    private float priceDat;

    public CTPDatDTO convertToDto(CTPDat detail) {
        CTPDatDTO deatailDTO = new CTPDatDTO();
//        deatailDTO.setDatId(detail.getCtpdhId().getDatId());
//        deatailDTO.setProductId(detail.getCtpdhId().getProductId());
        deatailDTO.setId(detail.getCtpdhId());
        deatailDTO.setQtyDat(detail.getQtyDat());
        deatailDTO.setPriceDat(detail.getPriceDat());


        return deatailDTO;
    }

    public CTPDat convertToEti(CTPDatDTO deatailDTO) {
        CTPDat detail = new CTPDat();

        detail.setCtpdhId(deatailDTO.getId());
//        detail.setDatId(deatailDTO.getDatId());
//        detail.setProductId(deatailDTO.getProductId());
        detail.setQtyDat(deatailDTO.getQtyDat());
        detail.setPriceDat(deatailDTO.getPriceDat());

        return detail;
    }


    public List<CTPDatDTO> toListDto(List<CTPDat> listEntity) {
        List<CTPDatDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }
}
