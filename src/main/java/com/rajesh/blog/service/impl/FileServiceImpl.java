package com.rajesh.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rajesh.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		// file name
		String name = file.getOriginalFilename();
		//abc.png
		
		
		//random name generate file 
		String randomID = UUID.randomUUID().toString();
		String fileName  = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		
		//fullPath
		String filePath = path + File.separator + fileName; 
		
		
		
		
		// create folder if not created 
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		
		
		//file copy 
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName;
	}

	@Override
	public InputStream getResourse(String path, String fileName) throws FileNotFoundException {
		
		String fullPath= File.separator+fileName;
		InputStream is = new FileInputStream(fullPath);
		//db logic to return inputStream 
		
		return is;
	}

}
