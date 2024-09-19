package com.persnal.boardback.service.implement;

import com.persnal.boardback.common.GlobalVariable;
import com.persnal.boardback.common.Utils;
import com.persnal.boardback.common.logger.DBLogger;
import com.persnal.boardback.service.FileService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    Logger logger = DBLogger.getInstance().getLogger();

    @Value("${file.save.path}")
    private String filepath;

    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        String url = "";
        try {
            if (file.isEmpty()) return null;

            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();

            String saveFileName = uuid + extension;
            String savePath = filepath + saveFileName;

            // 파일 저장.
            file.transferTo(new File(savePath));

            url = fileUrl + saveFileName;

        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return null;
        }

        return url;
    }

    @Override
    public Resource getImage(String fileName) {
        Resource resource = null;
        try {
            resource = new UrlResource("file:"+ filepath + fileName);

        }catch (Exception e){
            logger.error(GlobalVariable.logPattern,getClass().getName(), Utils.getStackTrace(e));
            return null;
        }
        return resource;
    }
}
