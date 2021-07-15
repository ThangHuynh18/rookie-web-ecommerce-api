package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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


    public ProductDTO(String productName, float productPrice, String productDescription, long productQty, long category_id) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productQty = productQty;
        this.category_id = category_id;
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

}
