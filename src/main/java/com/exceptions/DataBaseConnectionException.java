package com.exceptions;

public class DataBaseConnectionException extends RuntimeException{
    public DataBaseConnectionException(String message) {
        super(message);
    }
}
