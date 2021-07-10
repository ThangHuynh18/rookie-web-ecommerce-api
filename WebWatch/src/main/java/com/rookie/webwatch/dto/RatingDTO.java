package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.ProductRating;

import java.util.ArrayList;
import java.util.List;

public class RatingDTO {
    private int ratingNumber;
    private long user_id;
    private long product_id;

    public RatingDTO() {
    }


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


    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}
