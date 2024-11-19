package com.simeon.lab3.exceptions;

public class DBConnectException extends RuntimeException {
    public DBConnectException(String message) {
        super(message);
    }
}
