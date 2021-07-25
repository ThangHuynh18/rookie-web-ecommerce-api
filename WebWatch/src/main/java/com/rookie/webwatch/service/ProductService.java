package com.rookie.webwatch.service;


import com.rookie.webwatch.dto.OrderDTO;
import com.rookie.webwatch.dto.PageDTO;
import com.rookie.webwatch.dto.ProductDTO;
import com.rookie.webwatch.dto.ProductResponseDTO;
import com.rookie.webwatch.entity.Product;
import com.rookie.webwatch.exception.BadRequestException;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<ProductResponseDTO> retrieveProducts();

    public Optional<ProductDTO> getProduct(Long productId) throws ResourceNotFoundException;

    public ProductDTO saveProduct(ProductDTO productDTO) throws ResourceNotFoundException, BadRequestException;

    public Boolean deleteProduct(Long productId) throws ResourceNotFoundException;

    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) throws ResourceNotFoundException;

    public List<ProductDTO>sortProduct(PageDTO pageDTO);

    public List<ProductDTO> findProductByCate(Long categoryId, PageDTO pageDTO) throws ResourceNotFoundException;

    public List<ProductDTO> findProductByCategory(Long categoryId) throws ResourceNotFoundException;

    public List<ProductDTO> findProductByBrand(Long brandId) throws ResourceNotFoundException;

    public List<ProductDTO>searchProduct(String productName, Pageable pageable);

    public List<ProductDTO> searchProductByName(String name, Pageable pageable);

}
