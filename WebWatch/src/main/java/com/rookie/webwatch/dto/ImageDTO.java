package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.ProductImage;

import java.util.ArrayList;
import java.util.List;

public class ImageDTO {
    private String imageLink;

    private long product_id;
    public ImageDTO() {
    }

    public ImageDTO convertToDto(ProductImage image) {
        ImageDTO dto = new ImageDTO();
        dto.setImageLink(image.getImageLink());
        dto.setProduct_id(image.getProduct().getProduct_id());

        return dto;
    }

    public ProductImage convertToEti(ImageDTO dto) {
        ProductImage image = new ProductImage();

        image.setImageLink(dto.getImageLink());

        return image;
    }


    public List<ImageDTO> toListDto(List<ProductImage> listEntity) {
        List<ImageDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }

    public List<ProductImage> toListEntity(List<ImageDTO> listDto){
        List<ProductImage> listEntity  = new ArrayList<>();
        listDto.forEach(d->{
            listEntity.add(this.convertToEti(d));
        });
        return listEntity;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}
