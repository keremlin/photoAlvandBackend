package com.ara.photoalvand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class HandleException {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(FileIsNotDeleted.class)
    public String handleFileDeletion(FileIsNotDeleted ex){
        log.error(ex.getMessage());
        return ex.getMessage();
    }

}
