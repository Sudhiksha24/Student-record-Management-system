package com.wipro.srms.util;

public class DuplicateRecordException extends Exception {

    public DuplicateRecordException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DuplicateRecordException: " + getMessage();
    }
}
