package com.karljeong.fourtysix.application.admin.file.service;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.karljeong.fourtysix.database.entity.TbComFile;
import com.karljeong.fourtysix.database.repository.TbComFileRepository;
import com.karljeong.fourtysix.utils.DateUtil;

@Service
public class FileService {

    private final Path rootLocation;
    private TbComFileRepository tbComFileRepository;

    @Autowired
    public FileService(FileProperties fileProperties, TbComFileRepository tbComFileRepository) {
        this.rootLocation = Paths.get(fileProperties.getLocation());
        this.tbComFileRepository = tbComFileRepository;
    }

    public void uploadFile(MultipartFile multipartFile) {
        try {
            if(multipartFile.isEmpty()) {
                throw new Exception("Failed to upload empty file " + multipartFile.getOriginalFilename());
            }
            Path savePath = rootLocation.resolve(DateUtil.getDate("yyyyMMdd"));
            boolean fileExist = Files.exists(savePath, LinkOption.NOFOLLOW_LINKS);
            if(!fileExist) {
                Files.createDirectories(savePath);
            }

            String fileName = DateUtil.getDate("yyyyMMddHHmmssSSS");
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String saveFileName = String.format("%s.%s", fileName, extension);

            Files.copy(multipartFile.getInputStream(), savePath.resolve(saveFileName));

            TbComFile tbComFile = new TbComFile();
            tbComFile.setFileOrginalName(multipartFile.getOriginalFilename());
            tbComFile.setFilePath(savePath.toString());
            tbComFile.setFileName(fileName);
            tbComFile.setFileExtention(extension);
            tbComFile.setFileSize(BigInteger.valueOf(multipartFile.getSize()));
            tbComFile.setCreateDatetime(DateUtil.getTimestamp());
            tbComFile.setCreateUserId(BigInteger.valueOf(1111));
            tbComFileRepository.save(tbComFile);

        } catch (Exception e) {
        }
    }
}
