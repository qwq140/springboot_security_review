package com.example.springboot_security_review.modules.file.service;

import com.example.springboot_security_review.eunms.FileType;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.file.domain.entity.FileEntity;
import com.example.springboot_security_review.modules.file.domain.entity.FileListEntity;
import com.example.springboot_security_review.modules.file.repository.FileListRepository;
import com.example.springboot_security_review.modules.file.repository.FileRepository;
import com.example.springboot_security_review.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;
    private final FileListRepository fileListRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public FileDto saveFile(MultipartFile file, FileType type){

        FileEntity savedFile = fileRepository.save(
                FileEntity.builder()
                        .category(type)
                        .build());

        if(savedFile.getIdx() > 0){
            String url;
            try {
                url = uploadFile(file, savedFile.getIdx(), type);
                System.out.println("업로드 성공");
            } catch (IOException e) {
                System.out.println("업로드 실패");
                e.printStackTrace();
                return null;
            }

            String path = uploadFolder+url;
            String realUrl = "/upload/"+url;
            fileListEntitySave(file, realUrl, path, savedFile);

        } else {
            return null;
        }
        return savedFile.toDto();
    }

    @Transactional
    public FileDto saveFiles(FileType category, List<MultipartFile> files){
        FileEntity fileEntity = FileEntity.builder()
                .category(category)
                .build();

        FileEntity saveFile = fileRepository.save(fileEntity);
        if(saveFile.getIdx() > 0) {
            files.forEach(file -> {
                try {
                    String url = uploadFile(file, saveFile.getIdx(), category);
                    String path = uploadFolder+url;
                    String realUrl = "/upload/"+url;
                    fileListEntitySave(file, realUrl, path, saveFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return saveFile.toDto();
        } else {
            return null;
        }

    }

    @Transactional
    public FileDto saveAddFiles(FileType category, List<MultipartFile> files, Long masterIdx){
        Optional<FileEntity> optional = fileRepository.findById(masterIdx);

        if(optional.isPresent()) {
            files.forEach(file -> {
                try {
                    String url = uploadFile(file, optional.get().getIdx(), category);
                    String path = uploadFolder+url;
                    String realUrl = "/upload/"+url;
                    fileListEntitySave(file, realUrl, path, optional.get());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return optional.get().toDto();
        }
        return saveFiles(category, files);
    }


    @Transactional
    public void fileListEntitySave(MultipartFile file, String url, String path, FileEntity savedFile){
        FileListEntity toSaveFileList = FileListEntity.builder()
                .file(savedFile)
                .url(url)
                .path(path)
                .originalName(file.getOriginalFilename())
                .size(file.getSize())
                .extension(getExtension(file.getOriginalFilename()))
                .build();
        FileListEntity savedFileList = fileListRepository.save(toSaveFileList);
        savedFile.getFiles().add(savedFileList);
    }

    private String uploadFile(MultipartFile file, Long fileIdx, FileType category) throws IOException {
        String originalName = file.getOriginalFilename();
        String extension = getExtension(originalName);
        String uuid = UUID.randomUUID().toString();
        String savedName = Utils.getCurrentDate() + "_" + uuid + '.' +extension;

        String save = category.name()+"/"+fileIdx+"/"+savedName;

        Path directoryPath = Paths.get(uploadFolder+category.name()+"/"+fileIdx);
        Path imageFilePath = Paths.get(uploadFolder+save);

        Files.createDirectories(directoryPath);
        Files.write(imageFilePath, file.getBytes());

        return save;
    }

    private String getExtension(String fileName){
        if(fileName == null || fileName.equals("")){
            return "";
        }

        int index = fileName.indexOf(".");
        return fileName.substring(index+1);
    }

    public String editorUploadImage(MultipartFile file) throws IOException {

        String originalName = file.getOriginalFilename();
        String extension = getExtension(originalName);
        String uuid = UUID.randomUUID().toString();
        String savedName = Utils.getCurrentDate() + "_" + uuid + '.' +extension;

        String save = "editor/"+savedName;

        Path imageFilePath = Paths.get(uploadFolder+save);

        Files.write(imageFilePath, file.getBytes());

        return "/upload/" + save;
    }
}
