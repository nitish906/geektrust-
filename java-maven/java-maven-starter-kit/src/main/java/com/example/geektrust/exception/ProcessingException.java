package com.example.geektrust.exception;
/**
 * @author Simran Hotchandani
 * @date 29/10/22
 */

public class ProcessingException extends RuntimeException {
    public ProcessingException() {
        super();
    }

    public ProcessingException(String message) {
        super(message);
    }
}