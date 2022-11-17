package com.example.springboot_security_review.modules.product.domain.dto.response;

import com.example.springboot_security_review.eunms.ProductStatus;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileListDto;
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

}
