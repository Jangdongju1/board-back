package com.persnal.boardback.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    // 파일 업로드 관련 DB 작업
    String upload(MultipartFile file);
    Resource getImage(String fileName);
}
