package com.example.springboot_security_review.modules.product.repository;

import com.example.springboot_security_review.modules.product.domain.dto.response.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductRepositoryImpl implements ProductQueryDSLRepository{
    @Override
    public Page<ProductDto> pagingProductList(Pageable pageable) {
        return null;
    }
}
