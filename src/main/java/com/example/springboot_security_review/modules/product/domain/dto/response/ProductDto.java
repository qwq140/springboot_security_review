package com.example.springboot_security_review.modules.product.domain.dto.response;

import com.example.springboot_security_review.eunms.ProductStatus;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileListDto;
import com.example.springboot_security_review.modules.file.domain.entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long idx;
    private String name;
    private Integer stock;
    private Integer price;
    private ProductStatus status;
    private FileDto file;
    private FileDto thumbnail;
    private LocalDateTime createDate;

    public ProductDto(Long idx, String name, Integer stock, Integer price, ProductStatus status, FileEntity files, FileEntity thumbnail, LocalDateTime createDate){
        this.idx = idx;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.createDate = createDate;
        this.file = files == null ? null : files.toDto();
        this.thumbnail = thumbnail == null ? null : thumbnail.toDto();
    }

}
