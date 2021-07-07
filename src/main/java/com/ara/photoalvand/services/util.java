package com.ara.photoalvand.services;

import com.ara.photoalvand.controllers.FilesController;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

public class util {
    public static String convertFileNameToPath(String fileName) {
        return MvcUriComponentsBuilder.fromMethodName(FilesController.class, "getFile", fileName.toString()).build()
            .toString();
      }
    
}
