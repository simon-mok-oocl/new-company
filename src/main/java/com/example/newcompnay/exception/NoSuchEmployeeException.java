package com.example.newcompnay.exception;

public class NoSuchEmployeeException extends RuntimeException{
    public NoSuchEmployeeException() {
        super("No such employee");
    }
}
