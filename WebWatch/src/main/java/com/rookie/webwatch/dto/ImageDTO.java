package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.ProductImage;

public class ImageDTO {
    private String imageLink;

    public ImageDTO() {
    }

    public ImageDTO(ProductImage productImage){
        this.imageLink = productImage.getImageLink();

    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
