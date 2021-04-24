package ru.itis.springbootdemo.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.models.FileInfo;
import ru.itis.springbootdemo.repositories.FileInfoRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Value("${storage.name}")
    private String storagePath;

    @Override
    public String saveFile(MultipartFile uploadingFile) {
        String storageName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(uploadingFile.getOriginalFilename());
        FileInfo file = FileInfo.builder()
                .type(uploadingFile.getContentType())
                .originalFileName(uploadingFile.getOriginalFilename())
                .storageFileName(storageName)
                .size(uploadingFile.getSize())
                .url(storagePath + "/" + storageName)
                .build();

        try {
            Files.copy(uploadingFile.getInputStream(), Paths.get(storagePath, storageName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        fileInfoRepository.save(file);
        return String.format("/files/%s", storageName);
    }

    @Override
    public void writeFileToResponse(String filename, HttpServletResponse response) {
        FileInfo fileInfo = fileInfoRepository.findByStorageFileNameIs(filename);
        response.setContentType(fileInfo.getType());
        try {
            IOUtils.copy(new FileInputStream(fileInfo.getUrl()), response.getOutputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
