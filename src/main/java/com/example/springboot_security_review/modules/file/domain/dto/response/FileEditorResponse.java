package com.example.springboot_security_review.modules.file.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEditorResponse {
    private boolean uploaded;
    private String url;
}
