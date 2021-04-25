package com.fatih.hospitalappointment.config.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String value) {
        super("Record Not Found: "+value);
    }
}
