package com.fatih.hospitalappointment.config.exceptions;

public class RecordAlreadyExistException extends RuntimeException{
    public RecordAlreadyExistException(final String value) {
        super("Record Already Exist: "+value);
    }
}
