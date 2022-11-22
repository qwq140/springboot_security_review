package com.example.springboot_security_review.modules.product.service;

import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.product.domain.dto.request.ProductSaveDto;
import com.example.springboot_security_review.modules.product.domain.dto.response.ProductDto;
import com.example.springboot_security_review.modules.product.domain.entity.ProductEntity;
import com.example.springboot_security_review.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductDto productSave(ProductSaveDto dto, FileDto fileDto, FileDto thumbnail) {
        ProductEntity productEntity = productRepository.save(dto.toEntity(fileDto, thumbnail));
        return productEntity.toDto();
    }

    public void pagingProductList(Pageable pageable) {

    }

}
