package com.karljeong.fourtysix.application.admin.file.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.karljeong.fourtysix.database.entity.TbComFile;
import com.karljeong.fourtysix.database.repository.TbComFileRepository;
import com.karljeong.fourtysix.utils.DateUtil;

@Service
public class FileService {

	private final Path rootLocation;
	private ServletContext servletContext;
	private TbComFileRepository tbComFileRepository;

	@Autowired
	public FileService(FileProperties fileProperties, TbComFileRepository tbComFileRepository,
			ServletContext servletContext) {
		this.rootLocation = Paths.get(fileProperties.getLocation());
		this.tbComFileRepository = tbComFileRepository;
		this.servletContext = servletContext;
	}

	public TbComFile getFileInfo(BigInteger fileId) {
		return tbComFileRepository.findByFileId(fileId);
	}

	public List<TbComFile> getFileListByRefId(BigInteger fileRefId) {
		return tbComFileRepository.findByFileRefId(fileRefId);
	}

	public ResponseEntity<Resource> getFileResource(BigInteger fileId) {
		TbComFile selectFileInfo = tbComFileRepository.findByFileId(fileId);
		if (selectFileInfo == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		String filePath = FilenameUtils.concat(selectFileInfo.getFilePath(),
				selectFileInfo.getFileName() + "." + selectFileInfo.getFileExtension());
		String fileName = selectFileInfo.getFileOriginalName() + "." + selectFileInfo.getFileExtension();

		File file = new File(filePath);
		if (!file.exists()) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		String contentType = servletContext.getMimeType(file.getAbsolutePath());

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(new FileSystemResource(file));
	}

	public ResponseEntity<Resource> getImageResource(BigInteger fileId) throws FileNotFoundException {
		TbComFile selectFileInfo = tbComFileRepository.findByFileId(fileId);
		if (selectFileInfo == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		String filePath = FilenameUtils.concat(selectFileInfo.getFilePath(),
				selectFileInfo.getFileName() + "." + selectFileInfo.getFileExtension());
		File file = new File(filePath);
		if (!file.exists()) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		FileInputStream fis = new FileInputStream(file);
		InputStreamResource isr = new InputStreamResource(fis);
		String contentType = servletContext.getMimeType(file.getAbsolutePath());

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(isr);
	}

	public List<TbComFile> uploadFiles(String fileType, MultipartHttpServletRequest req) {
		return this.uploadFiles(null, fileType, req);
	}

	public List<TbComFile> uploadFiles(BigInteger fileRefId, String fileType, MultipartHttpServletRequest req) {
		List<TbComFile> tbComFileList = new ArrayList<TbComFile>();
		Map<String, MultipartFile> fileMap = req.getFileMap();

		int i = 1;
		for (String key : fileMap.keySet()) {
			tbComFileList.add(this.uploadFile(fileRefId, fileType, i, fileMap.get(key)));
			i++;
		}
		return tbComFileList;
	}

	public TbComFile uploadFile(BigInteger fileRefId, String fileType, int fileOrder, MultipartFile multipartFile) {
		TbComFile tbComFile = new TbComFile();
		try {
			if (multipartFile.isEmpty()) {
				throw new Exception("Failed to upload empty file " + multipartFile.getOriginalFilename());
			}
			Path savePath = rootLocation.resolve(fileType + "/" + DateUtil.getDate("yyyyMMdd"));
			boolean fileExist = Files.exists(savePath, LinkOption.NOFOLLOW_LINKS);
			if (!fileExist) {
				Files.createDirectories(savePath);
			}

			String fileName = DateUtil.getDate("yyyyMMddHHmmssSSS");
			String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			String saveFileName = String.format("%s.%s", fileName, extension);

			Files.copy(multipartFile.getInputStream(), savePath.resolve(saveFileName));

			if (fileRefId != null) {
				tbComFile.setFileRefId(fileRefId);
			}
			tbComFile.setFileType(fileType);
			tbComFile.setFileOrder(1);
			tbComFile.setFileOriginalName(multipartFile.getOriginalFilename());
			tbComFile.setFilePath(savePath.toString());
			tbComFile.setFileName(fileName);
			tbComFile.setFileExtension(extension);
			tbComFile.setFileSize(BigInteger.valueOf(multipartFile.getSize()));
			tbComFile.setCreateDatetime(DateUtil.getTimestamp());
			tbComFile.setCreateUserId(BigInteger.valueOf(1));
            tbComFile = tbComFileRepository.save(tbComFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tbComFile;
	}

	public TbComFile save(TbComFile tbComFile) {
	    return tbComFileRepository.save(tbComFile);
	}

	public int removeFile(BigInteger fileId) {
		return tbComFileRepository.deleteFile(fileId);
	}

}
