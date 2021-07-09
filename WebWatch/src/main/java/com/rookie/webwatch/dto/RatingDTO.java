package com.rookie.webwatch.dto;

public class RatingDTO {
    private int ratingNumber;
    private long user_id;
    private long product_id;

    public RatingDTO() {
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
