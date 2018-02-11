package com.imooc.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.dto.FileInfo;

@RestController
@RequestMapping("/files")
public class FileController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String folder = "C:/";
	
	@PostMapping
	public FileInfo upload(MultipartFile file) throws Exception {
		logger.info(file.getName()); //参数名
		logger.info(file.getOriginalFilename()); //原始文件名
		logger.info(file.getSize() + ""); //文件尺寸
		
		File localFile = new File(folder, System.currentTimeMillis() + ".txt");
		file.transferTo(localFile);
		
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
				OutputStream outputStream = response.getOutputStream();) {
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			IOUtils.copy(inputStream, outputStream);
		} catch (Exception e) {
			throw e;
		}
	}
}
