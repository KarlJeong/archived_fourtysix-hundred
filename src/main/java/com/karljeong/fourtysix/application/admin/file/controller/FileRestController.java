package com.karljeong.fourtysix.application.admin.file.controller;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.karljeong.fourtysix.application.admin.file.service.FileService;
import com.karljeong.fourtysix.database.entity.TbComFile;

@RestController
@RequestMapping("/v1/api/file")
public class FileRestController {

	FileService fileService;

	@Autowired
	public FileRestController(FileService fileService) {
		this.fileService = fileService;
	}

	@GetMapping("/f/{fileId}")
	public TbComFile getFileInfo(@PathVariable BigInteger fileId, HttpServletRequest req) throws FileNotFoundException {
		return fileService.getFileInfo(fileId);
	}

	@GetMapping("/f/{fileRefId}")
	public List<TbComFile> getFileListByKey(@PathVariable BigInteger fileRefId, HttpServletRequest req)
			throws FileNotFoundException {
		return fileService.getFileListByRefId(fileRefId);
	}

	@PostMapping("/{fileType}")
	public List<TbComFile> uploadFile(@PathVariable String fileType, MultipartHttpServletRequest req) {
		return fileService.uploadFiles(fileType, req);
	}

	@PostMapping("/{fileRefId}/{fileType}")
	public List<TbComFile> uploadFile(@PathVariable BigInteger fileRefId, @PathVariable String fileType,
			MultipartHttpServletRequest req) {
		return fileService.uploadFiles(fileRefId, fileType, req);
	}

	@GetMapping("/download/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable BigInteger fileId, HttpServletRequest req)
			throws FileNotFoundException {
		return fileService.getFileResource(fileId);
	}

	@GetMapping("/img/{fileId}")
	public ResponseEntity<Resource> getImageResource(@PathVariable BigInteger fileId, HttpServletRequest req)
			throws FileNotFoundException {
		return fileService.getImageResource(fileId);
	}

	@DeleteMapping("/{fileId}")
	public int removeFile(@PathVariable BigInteger fileId) {
		return fileService.removeFile(fileId);
	}

	/*
	 * @PostMapping public String uploadFile(MultipartHttpServletRequest
	 * multipartHttpServletRequest) { Map<String, MultipartFile> fileMap =
	 * multipartHttpServletRequest.getFileMap(); fileMap.keySet().forEach(key ->
	 * fileService.uploadFile(fileMap.get(key))); return "OK"; }
	 */
}
