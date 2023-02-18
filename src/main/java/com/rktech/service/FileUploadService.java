package com.rktech.service;

import com.rktech.entity.FileEntity;
import com.rktech.model.FileModel;
import com.rktech.repository.FileRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FileUploadService {

    private final FileRepository repository;

    @Transactional
    public void upload(FileModel fileModel) {
        try {
            FileEntity file = FileEntity.builder()
                    .fileName(fileModel.getResource().getFilename())
                    .content(fileModel.getContent())
                    .build();
            FileEntity fileEntity = repository.save(file);
            log.info("File content : {}", fileModel.getResource().getFilename());
            FileEntity fileFromDb = repository.findById(fileEntity.getId()).orElse(new FileEntity());
            log.info("File from DB  ID : {}, FileName : {}", fileFromDb.getId(), fileFromDb.getFileName());
            log.info("isEqual : {}", fileModel.getContent().equalsIgnoreCase(fileFromDb.getContent()));
        } catch (Exception ex) {
            log.error("Exception : {}", ex.getMessage());
        }
    }
}
