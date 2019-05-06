package com.practicetask.netflixandspringbaby.exception;

public class NoEmployeeFoundException extends RuntimeException {
    public NoEmployeeFoundException() {
    }

    public NoEmployeeFoundException(String message) {
        super(message);
    }
}