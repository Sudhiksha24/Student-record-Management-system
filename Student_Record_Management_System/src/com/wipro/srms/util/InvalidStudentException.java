package com.wipro.srms.util;

public class InvalidStudentException extends Exception {

    public InvalidStudentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidStudentException: " + getMessage();
    }
}
