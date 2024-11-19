package com.simeon.lab3.exceptions;

public class DBWritingException extends RuntimeException {
    public DBWritingException() {
        super("Error writing to the database");
    }
}
