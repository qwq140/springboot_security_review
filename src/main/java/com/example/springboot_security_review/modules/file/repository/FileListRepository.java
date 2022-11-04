package com.example.springboot_security_review.modules.file.repository;

import com.example.springboot_security_review.modules.file.domain.entity.FileListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileListRepository extends JpaRepository<FileListEntity,Long> {
}
