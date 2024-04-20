package com.example.nnpia_semestralka.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile image) {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\images\\" + image.getOriginalFilename());
            try {
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return image.getOriginalFilename();
    }
}
