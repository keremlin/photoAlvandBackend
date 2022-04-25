package com.ara.photoalvand.services;

import java.util.Random;

import com.ara.photoalvand.controllers.FilesController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

public class util {
    

    public static String convertFileNameToPath(String fileName) {
        return MvcUriComponentsBuilder.fromMethodName(FilesController.class, "getFile", fileName.toString()).build()
                .toString();
    }

    public static int RandomNumberBetween(int A, int B) {
        Random r = new Random();
        if (A == B)
            return A;
        return (B - A >= 1 && A >= 0 && B >= 0 ? (r.nextInt(B - A) + A) : 0);
    }
}
