package com.example.newcompnay.exception;

public class NoSuchCompanyException extends RuntimeException{
    public NoSuchCompanyException() {
        super("No such employee");
    }
}