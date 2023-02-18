package com.rktech.controller;

import com.rktech.model.FileModel;
import com.rktech.service.FileUploadService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class FileUploadController {

    private final FileUploadService service;

    @SneakyThrows
    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String fileContent = Base64Utils.encodeToString(file.getBytes());
        FileModel fileModel = FileModel.builder()
                .content(fileContent)
                .name(file.getName())
                .resource(file.getResource())
                .build();
        service.upload(fileModel);
        return ResponseEntity.ok("File uploaded successfully!");
    }
}
