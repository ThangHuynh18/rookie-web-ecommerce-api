package com.rookie.webwatch.convert;

import com.rookie.webwatch.dto.request.ProductDtoRequest;
import com.rookie.webwatch.dto.response.ProductDtoResponse;
import com.rookie.webwatch.entity.Category;
import com.rookie.webwatch.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductConvert {
    @Autowired
    private ModelMapper modelMapper;

    public ProductDtoResponse convertToDto(Optional<Product> product) {
        ProductDtoResponse productDtoResponse = modelMapper.map(product, ProductDtoResponse.class);
        productDtoResponse.setProductName(product.get().getProductName());
        productDtoResponse.setProductPrice(product.get().getProductPrice());
        productDtoResponse.setProductDescription(product.get().getProductDescription());

        return productDtoResponse;
    }


    public ProductDtoResponse convertToDtoForUpdate(Product product) {
        ProductDtoResponse productDtoResponse = modelMapper.map(product, ProductDtoResponse.class);
        productDtoResponse.setProductName(product.getProductName());
        productDtoResponse.setProductPrice(product.getProductPrice());
        productDtoResponse.setProductDescription(product.getProductDescription());

        return productDtoResponse;
    }


    public Product convertToEntity(ProductDtoRequest productDtoRequest) {
        Product product = modelMapper.map(productDtoRequest, Product.class);
//        product.setProductName(productDtoRequest.getProductName());
//        product.setProductPrice(productDtoRequest.getProductPrice());
//        product.setProductQty(productDtoRequest.getProductQty());
//        product.setProductDescription(productDtoRequest.getProductDescription());
//        product.getCategory().setCategory_id(productDtoRequest.getCategory_id());

        return product;
    }
}
