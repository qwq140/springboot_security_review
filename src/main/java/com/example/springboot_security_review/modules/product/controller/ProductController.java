package com.example.springboot_security_review.modules.product.controller;

import com.example.springboot_security_review.modules.product.domain.dto.response.ProductDto;
import com.example.springboot_security_review.modules.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String productList(@PageableDefault(sort = "idx", direction = Sort.Direction.DESC, size = 9) Pageable pageable,
                              Model model){
        model.addAttribute("productList", productService.pagingProductList(pageable));
        return "product/product_list";
    }
}
