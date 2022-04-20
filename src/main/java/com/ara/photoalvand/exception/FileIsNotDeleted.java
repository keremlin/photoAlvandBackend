package com.ara.photoalvand.exception;

public class FileIsNotDeleted extends RuntimeException {
    public FileIsNotDeleted(String message){
        super(message);
    }
    public FileIsNotDeleted(){
        super("file deletion failed because of OS file system error or file is currently ordered.");
    }
    
}
