package com.karljeong.fourtysix.application.admin.file.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.karljeong.fourtysix.application.admin.file.service.FileService;

@RestController
@RequestMapping("/file")
public class FileRestController {

    FileService fileService;

    @Autowired
    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String uploadFile(MultipartHttpServletRequest multipartHttpServletRequest) {
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        fileMap.keySet().forEach(key -> fileService.uploadFile(fileMap.get(key)));
        return "OK";
    }


}
