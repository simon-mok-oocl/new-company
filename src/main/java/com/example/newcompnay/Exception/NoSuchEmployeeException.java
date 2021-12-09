package com.example.newcompnay.Exception;

import java.util.function.Supplier;

public class NoSuchEmployeeException extends RuntimeException{
    public NoSuchEmployeeException() {
        super("No such employee");
    }
}
