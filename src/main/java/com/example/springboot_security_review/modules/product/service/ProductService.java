package com.example.springboot_security_review.modules.product.service;

import com.example.springboot_security_review.handler.exception.CustomException;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.product.domain.dto.request.ProductSaveDto;
import com.example.springboot_security_review.modules.product.domain.dto.response.ProductDto;
import com.example.springboot_security_review.modules.product.domain.entity.ProductEntity;
import com.example.springboot_security_review.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public Page<ProductDto> pagingProductList(Pageable pageable) {
        return productRepository.pagingProductList(pageable);
    }

    public ProductDto getProductDetail(Long idx) {
        ProductEntity productEntity = productRepository.findById(idx).orElseThrow(() -> new CustomException("해당 상품을 찾을 수 없습니다."));
        return productEntity.toDto();
    }


}
