package com.example.nnpia_semestralka.Service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile image);
}
