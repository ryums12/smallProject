package com.ryums.bookmark.controller;

import com.ryums.bookmark.utils.files.StorageFileNotFoundException;
import com.ryums.bookmark.utils.files.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Controller
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws UnsupportedEncodingException {

		Resource file = storageService.loadAsResource(filename);
		String fileName = file.getFilename();
		String encodeFileName = fileName;

		try {
			encodeFileName = URLEncoder.encode(fileName,"UTF-8");
			encodeFileName = encodeFileName.replaceAll("\\+", " ");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + encodeFileName + "\"").body(file);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
