package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProductDTO {
    private long product_id;

    @NotNull
    private String productName;

    @NotNull
    private float productPrice;

    @NotNull
    private String productDescription;

    @NotNull
    private long productQty;
    private long category_id;

    private List<ImageDTO> imageDTOS;

    //private List<RatingDTO> ratingDTOS;
    //private Set<Long> productRatings;

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


        List<ImageDTO> listDto = new ArrayList<>();

        if(product.getProductImages()!=null){
            product.getProductImages().forEach(e -> {
                listDto.add(new ImageDTO().convertToDto(e));
            });
        }
        productDTO.setImageDTOS(listDto);


//        List<RatingDTO> ratings = new ArrayList<>();
//        product.getProductRatings().forEach(rating -> {
//            ratings.add(new RatingDTO().convertToDto(rating));
//        });
//        productDTO.setRatingDTOS(ratings);

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

    public List<ImageDTO> getImageDTOS() {
        return imageDTOS;
    }

    public void setImageDTOS(List<ImageDTO> imageDTOS) {
        this.imageDTOS = imageDTOS;
    }

//    public Set<Long> getProductRatings() {
//        return productRatings;
//    }
//
//    public void setProductRatings(Set<Long> productRatings) {
//        this.productRatings = productRatings;
//    }

//    public List<RatingDTO> getRatingDTOS() {
//        return ratingDTOS;
//    }
//
//    public void setRatingDTOS(List<RatingDTO> ratingDTOS) {
//        this.ratingDTOS = ratingDTOS;
//    }
}
