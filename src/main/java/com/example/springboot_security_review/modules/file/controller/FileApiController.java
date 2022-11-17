package com.example.springboot_security_review.modules.file.controller;

import com.example.springboot_security_review.eunms.FileType;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileEditorResponse;
import com.example.springboot_security_review.modules.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/v1/api/file")
@RequiredArgsConstructor
public class FileApiController {

    private final FileService fileService;

    // 단일 이미지 업로드
    @PostMapping
    public HttpEntity<?> uploadImageFile(@RequestParam(name = "file") MultipartFile file, @RequestParam(required = false) FileType type, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/").concat("resources");
        System.out.println(path+File.separator+"upload");
        FileDto fileDto = fileService.saveFile(file, type);
        if(fileDto == null){
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(fileDto, HttpStatus.OK);
    }

    @PostMapping("/editor")
    public HttpEntity<?> editorImageUpload(MultipartHttpServletRequest request) {
        MultipartFile file = request.getFile("upload");
        try {
            return new ResponseEntity<>(FileEditorResponse.builder().uploaded(true).url(fileService.editorUploadImage(file)).build(), HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("test");
            throw new RuntimeException(e);
        }
    }
}
