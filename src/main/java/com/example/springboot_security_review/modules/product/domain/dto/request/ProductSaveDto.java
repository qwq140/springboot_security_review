package com.example.springboot_security_review.modules.product.domain.dto.request;

import com.example.springboot_security_review.eunms.ProductStatus;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.product.domain.entity.ProductEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.List;

@Data
public class ProductSaveDto {

    @NotBlank(message = "상품명을 입력해주세요")
    private String name;

    @NotBlank(message = "가격을 입력해주세요")
    private Integer price;

    @NotBlank(message = "재고 수량을 입력해주세요")
    private Integer stock;

    @NotBlank(message = "판매 상태를 선택해주세요")
    private ProductStatus productStatus;

    private MultipartFile thumbnail;
    private List<MultipartFile> images;

    public ProductEntity toEntity(FileDto fileDto, FileDto thumbnail) {
        return ProductEntity.builder()
                .name(name)
                .status(productStatus)
                .price(price)
                .stock(stock)
                .file(fileDto != null ? fileDto.toEntity() : null)
                .thumbnail(thumbnail != null ? thumbnail.toEntity() : null)
                .build();
    }
}
