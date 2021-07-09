package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Order;
import com.rookie.webwatch.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDTO {
    private long product_id;
    private String productName;
    private float productPrice;
    private String productDescription;
    private long productQty;
    private long category_id;
    private Set<ImageDTO> productImages;
    private Set<RatingDTO> productRatings;

    public ProductDTO() {
    }


    public ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_id(product.getProduct_id());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductQty(product.getProductQty());
        productDTO.setCategory_id(product.getCategory().getCategory_id());

        //productDTO.setProductImages(product.getProductImages());
        return productDTO;
    }

    public Product convertToEti(ProductDTO productDTO) {
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductQty(productDTO.getProductQty());

        return product;
    }


    public List<ProductDTO> toListDto(List<Product> listEntity) {
        List<ProductDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }


    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
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

    public Set<ImageDTO> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ImageDTO> productImages) {
        this.productImages = productImages;
    }

    public Set<RatingDTO> getProductRatings() {
        return productRatings;
    }

    public void setProductRatings(Set<RatingDTO> productRatings) {
        this.productRatings = productRatings;
    }
}
