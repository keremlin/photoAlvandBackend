package com.ara.photoalvand.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message){
        super (message);
    }
    public RecordNotFoundException(){
        super();
    }
}
