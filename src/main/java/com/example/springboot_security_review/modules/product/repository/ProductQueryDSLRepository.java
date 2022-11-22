package com.example.springboot_security_review.modules.product.repository;

import com.example.springboot_security_review.modules.product.domain.dto.response.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryDSLRepository {

    Page<ProductDto> pagingProductList(Pageable pageable);

}
