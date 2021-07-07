package com.ara.photoalvand;

import javax.annotation.Resource;

import com.ara.photoalvand.services.FilesStorageService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotoalvandApplication {
	@Resource
	FilesStorageService storageService;
  
	public static void main(String[] args) {
	  SpringApplication.run(PhotoalvandApplication.class, args);
	}
  
	

}
