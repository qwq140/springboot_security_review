package com.example.springboot_security_review.modules.file.service;

import com.example.springboot_security_review.eunms.FileType;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.file.domain.entity.FileEntity;
import com.example.springboot_security_review.modules.file.domain.entity.FileListEntity;
import com.example.springboot_security_review.modules.file.repository.FileListRepository;
import com.example.springboot_security_review.modules.file.repository.FileRepository;
import com.example.springboot_security_review.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;
    private final FileListRepository fileListRepository;

    String path;

    @Transactional
    public FileDto saveFile(MultipartFile file, FileType type, HttpServletRequest request){
        path = request.getSession().getServletContext().getRealPath("");
        System.out.println(path);

        FileEntity savedFile = fileRepository.save(
                FileEntity.builder()
                        .category(type)
                        .build());

        if(savedFile.getIdx() > 0){
            String url;
            try {
                url = uploadFile(file, savedFile.getIdx());
                System.out.println("업로드 성공");
            } catch (IOException e) {
                System.out.println("업로드 실패");
                e.printStackTrace();
                return null;
            }

            FileListEntity toSaveFileList = FileListEntity.builder()
                    .url(url)
                    .extension(getExtension(file.getOriginalFilename()))
                    .size(file.getSize())
                    .originalName(file.getOriginalFilename())
                    .file(savedFile)
                    .build();

            FileListEntity savedFileList = fileListRepository.save(toSaveFileList);
            savedFile.setFiles(Arrays.asList(savedFileList));

        } else {
            return null;
        }
        return savedFile.toDto();
    }

    private String uploadFile(MultipartFile file, Long fileIdx) throws IOException {
        String originalName = file.getOriginalFilename();
        String extension = getExtension(originalName);
        String uuid = UUID.randomUUID().toString();
        String savedName = Utils.getCurrentDate() + "_" + uuid + '.' +extension;

        String imagePathUrl = path+"upload"+File.separator+fileIdx+File.separator+savedName;

        File file1 = new File(imagePathUrl);
        file1.getParentFile().mkdirs();
        file.transferTo(file1);
        return imagePathUrl;
    }

    private String getExtension(String fileName){
        if(fileName == null || fileName.equals("")){
            return "";
        }

        int index = fileName.indexOf(".");
        return fileName.substring(index+1);
    }
}
