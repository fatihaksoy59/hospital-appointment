package com.fatih.hospitalappointment.config.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(final String value) {
        super("Record Not Found: " + value);
    }
}
