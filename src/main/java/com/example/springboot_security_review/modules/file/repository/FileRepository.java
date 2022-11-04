package com.example.springboot_security_review.modules.file.repository;

import com.example.springboot_security_review.modules.file.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
