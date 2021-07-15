package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.ProductRating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RatingDTO {
    private int ratingNumber;
    private long user_id;
    private long product_id;


    public RatingDTO convertToDto(ProductRating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRatingNumber((int) rating.getRatingNumber());
        ratingDTO.setUser_id(rating.getUser().getUser_id());
        ratingDTO.setProduct_id(rating.getProduct().getProduct_id());

        return ratingDTO;
    }

    public ProductRating convertToEti(RatingDTO ratingDTO) {
        ProductRating rating = new ProductRating();

        rating.setRatingNumber(ratingDTO.getRatingNumber());

        return rating;
    }


    public List<RatingDTO> toListDto(List<ProductRating> listEntity) {
        List<RatingDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }

    public List<ProductRating> toListEntity(List<RatingDTO> listDto) {
        List<ProductRating> listEntity = new ArrayList<>();

        listDto.forEach(r->{
            listEntity.add(this.convertToEti(r));
        });
        return listEntity;
    }

}
