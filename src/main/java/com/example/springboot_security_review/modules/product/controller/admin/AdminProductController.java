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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String productList(@PageableDefault(sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
                              Model model){
        model.addAttribute("productList", productService.pagingProductList(pageable));
        return "admin/product/product_list";
    }

    @GetMapping("/form")
    public String saveForm(){
        return "admin/product/product_form";
    }

    /// TODO : ????????? ????????? ??? ?????? ????????? ????????????
    /// ????????? ????????? ??????
    @PostMapping
    public String save(@Valid ProductSaveDto productSaveDto, BindingResult bindingResult){
        // file input??? ??????????????? ???????????? ????????? ????????? ?????????????????? ????????? filter??? ????????? ????????? ???????????? ?????????
        productSaveDto.setImages(productSaveDto.getImages().stream().filter(file -> !file.getOriginalFilename().isEmpty()).collect(Collectors.toList()));

        FileDto fileDto = null;
        FileDto thumbnail = null;
        // ????????? ??????
        if(productSaveDto.getThumbnail() != null) {
            thumbnail = fileService.saveFile(productSaveDto.getThumbnail(), FileType.PRODUCT);
        }

        // ?????? ???????????? ??????
        if(productSaveDto.getImages() != null && !productSaveDto.getImages().isEmpty()){
            fileDto = fileService.saveFiles(FileType.PRODUCT, productSaveDto.getImages());
        }

        ProductDto productDto = productService.productSave(productSaveDto, fileDto, thumbnail);

        if(productDto == null) {
            throw new CustomException("????????? ?????? ??????????????????");
        }

        return "redirect:/v1/admin/product";
    }

}
