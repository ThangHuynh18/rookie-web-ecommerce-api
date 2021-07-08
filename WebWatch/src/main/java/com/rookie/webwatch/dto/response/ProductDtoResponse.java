package com.rookie.webwatch.dto.response;

import com.rookie.webwatch.dto.ImageDTO;
import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.entity.ProductImage;

import java.util.Set;

public class ProductDtoResponse {
    private String productName;
    private float productPrice;
    private String productDescription;
    private Set<ImageDTO> productImages;

    public ProductDtoResponse() {
    }

//    public ProductDtoResponse(Product product){
//        this.setProductName(product.getProductName());
//        this.setProductPrice(product.getProductPrice());
//        this.setProductDescription(product.getProductDescription());
//        this.setImageDTOS(product.getProductImages());
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Set<ImageDTO> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ImageDTO> productImages) {
        this.productImages = productImages;
    }

    //    public Set<ProductImage> getProductImages() {
//        return productImages;
//    }
//
//    public void setProductImages(Set<ProductImage> productImages) {
//        this.productImages = productImages;
//    }
}
