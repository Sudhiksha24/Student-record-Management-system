package com.wipro.srms.util;

public class RecordNotFoundException extends Exception {

    public RecordNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "RecordNotFoundException: " + getMessage();
    }
}
