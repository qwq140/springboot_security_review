package com.example.springboot_security_review.modules.product.repository;

import com.example.springboot_security_review.modules.product.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
