package com.ara.photoalvand.exception;

public class RecordPersistanceException extends RuntimeException{
    public RecordPersistanceException(String message){
        super(message);
    }
    public RecordPersistanceException(){
        super();
    }
}
