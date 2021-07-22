package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDTO {
    private long product_id;

    @NotBlank
    private String productName;

    @NotNull
    @Min(value = 1)
    private float productPrice;

    @NotBlank
    private String productDescription;

    @NotNull
    @Min(value = 1)
    private long productQty;
    private String categoryName;
    private String brandName;

    private List<ImageDTO> imageDTOS;

    private List<RatingDTO> ratingDTOS;
    //private Set<Long> productRatings;



    public ProductResponseDTO convertToDto(Product product) {
        ProductResponseDTO productDTO = new ProductResponseDTO();
        productDTO.setProduct_id(product.getProduct_id());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductQty(product.getProductQty());
        productDTO.setCategoryName(product.getCategory().getCategoryName());
        productDTO.setBrandName(product.getBrand().getBrandName());

        List<ImageDTO> dtos = new ArrayList<>();

        if(product.getProductImages()!=null){
            product.getProductImages().forEach(e -> {
                dtos.add(new ImageDTO().convertToDto(e));
            });
        }
        productDTO.setImageDTOS(dtos);


        List<RatingDTO> ratings = new ArrayList<>();
        if(product.getProductRatings()!=null) {
            product.getProductRatings().forEach(rating -> {
                ratings.add(new RatingDTO().convertToDto(rating));
            });
        }
        productDTO.setRatingDTOS(ratings);

        return productDTO;
    }

    public Product convertToEti(ProductResponseDTO productDTO) {
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductQty(productDTO.getProductQty());

        return product;
    }


    public List<ProductResponseDTO> toListDto(List<Product> listEntity) {
        List<ProductResponseDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }
}
