package com.rookie.webwatch.dto.request;

import com.rookie.webwatch.dto.CategoryDTO;

public class ProductDtoRequest {
    private String productName;
    private float productPrice;
    private String productDescription;
    private long productQty;
    private long category_id;

    public ProductDtoRequest() {
    }

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

    public long getProductQty() {
        return productQty;
    }

    public void setProductQty(long productQty) {
        this.productQty = productQty;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    //    public CategoryDTO getCategoryDTO() {
//        return categoryDTO;
//    }
//
//    public void setCategoryDTO(CategoryDTO categoryDTO) {
//        this.categoryDTO = categoryDTO;
//    }
}
