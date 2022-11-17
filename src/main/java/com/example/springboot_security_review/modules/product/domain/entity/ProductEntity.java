package com.example.springboot_security_review.modules.product.domain.entity;

import com.example.springboot_security_review.eunms.ProductStatus;
import com.example.springboot_security_review.modules.file.domain.entity.FileEntity;
import com.example.springboot_security_review.modules.file.domain.entity.FileListEntity;
import com.example.springboot_security_review.modules.product.domain.dto.response.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @OneToOne
    @JoinColumn(name = "file_idx", referencedColumnName = "idx")
    private FileEntity file;

    @OneToOne
    @JoinColumn(name = "thumbnail_idx", referencedColumnName = "idx")
    private FileEntity thumbnail;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    public ProductDto toDto(){
        return ProductDto.builder()
                .idx(idx)
                .name(name)
                .price(price)
                .stock(stock)
                .status(status)
                .thumbnail(thumbnail == null ? null : thumbnail.toDto())
                .file(file == null ? null : file.toDto())
                .build();
    }
}
