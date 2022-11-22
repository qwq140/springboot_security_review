package com.example.springboot_security_review.modules.product.controller.admin;

import com.example.springboot_security_review.eunms.FileType;
import com.example.springboot_security_review.eunms.ProductStatus;
import com.example.springboot_security_review.handler.exception.CustomException;
import com.example.springboot_security_review.handler.exception.CustomValidationException;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileListDto;
import com.example.springboot_security_review.modules.file.service.FileService;
import com.example.springboot_security_review.modules.product.domain.dto.request.ProductSaveDto;
import com.example.springboot_security_review.modules.product.domain.dto.response.ProductDto;
import com.example.springboot_security_review.modules.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/v1/admin/product")
@RequiredArgsConstructor
@Controller
public class AdminProductController {


    private final FileService fileService;
    private final ProductService productService;

    @GetMapping
    public String list(){
        return "product/product_list";
    }

    @GetMapping("/form")
    public String saveForm(){
        return "product/product_form";
    }

    /// TODO : 이미지 업로드 때 에러 발생시 처리하기
    /// 저장된 이미지 삭제
    @PostMapping
    public String save(@Valid ProductSaveDto productSaveDto, BindingResult bindingResult){
        // file input이 여러개인데 아무것도 안넣고 보내도 포함되어있음 그래서 filter를 통해서 이미지 있는것만 가져옴
        productSaveDto.setImages(productSaveDto.getImages().stream().filter(file -> !file.getOriginalFilename().isEmpty()).collect(Collectors.toList()));

        FileDto fileDto = null;
        FileDto thumbnail = null;
        // 썸네일 저장
        if(productSaveDto.getThumbnail() != null) {
            thumbnail = fileService.saveFile(productSaveDto.getThumbnail(), FileType.PRODUCT);
        }

        // 내용 이미지들 저장
        if(productSaveDto.getImages() != null && !productSaveDto.getImages().isEmpty()){
            fileDto = fileService.saveFiles(FileType.PRODUCT, productSaveDto.getImages());
        }

        ProductDto productDto = productService.productSave(productSaveDto, fileDto, thumbnail);

        if(productDto == null) {
            throw new CustomException("잠시후 다시 시도해주세요");
        }

        return "redirect:/v1/admin/product";
    }

}
