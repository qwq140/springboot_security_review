package com.example.springboot_security_review.modules.product.repository;

import com.example.springboot_security_review.modules.product.domain.dto.response.ProductDto;
import com.example.springboot_security_review.modules.product.domain.entity.ProductEntity;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.example.springboot_security_review.modules.product.domain.entity.QProductEntity.productEntity;
import static com.example.springboot_security_review.modules.file.domain.entity.QFileEntity.fileEntity;
import static com.example.springboot_security_review.modules.file.domain.entity.QFileListEntity.fileListEntity;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductQueryDSLRepository{

    private final JPAQueryFactory query;

    @Override
    public Page<ProductDto> pagingProductList(Pageable pageable) {
        // 1. 조건에 맞는 쿼리 작성
        JPAQuery<ProductDto> productDtoJPAQuery = query.select(Projections.constructor(ProductDto.class,
                        productEntity.idx,
                        productEntity.name,
                        productEntity.stock,
                        productEntity.price,
                        productEntity.status,
                        fileEntity,
                        fileEntity,
                        productEntity.createDate)
                )
                .from(productEntity)
                .leftJoin(fileEntity).on(productEntity.file.idx.eq(fileEntity.idx));

        // 2. fetchCount 실행 후 전체 개수 저장
        long totalCount = productDtoJPAQuery.fetch().size();

        // 3. 1번 쿼리에 offset, limit, order by 옵션을 넣어줌
        productDtoJPAQuery
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()){
            PathBuilder<ProductEntity> pathBuilder = new PathBuilder<>(productEntity.getType(), productEntity.getMetadata());
            productDtoJPAQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }

        // 4. 3번쿼리 fetch or fetchOne 실행
        List<ProductDto> productDtoList = productDtoJPAQuery.fetch();

        // 5. 해당 데이터 리턴
        return new PageImpl<>(productDtoList, pageable, totalCount);
    }
}
