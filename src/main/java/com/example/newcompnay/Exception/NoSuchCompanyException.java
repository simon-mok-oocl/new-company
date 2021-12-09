package com.example.newcompnay.Exception;

public class NoSuchCompanyException extends RuntimeException{
    public NoSuchCompanyException() {
        super("No such employee");
    }
}