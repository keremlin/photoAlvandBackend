package com.ara.photoalvand.services;

import java.util.Random;

import com.ara.photoalvand.controllers.FilesController;
import com.ara.photoalvand.repository.categoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

public class util {
    

    public static String convertFileNameToPath(String fileName) {
        return MvcUriComponentsBuilder.fromMethodName(FilesController.class, "getFile", fileName.toString()).build()
                .toString();
    }

    public static int RandomNumberBetween(int A, int B) {
        Random r = new Random();
        return (r.nextInt(B - A) + A);
    }
}
