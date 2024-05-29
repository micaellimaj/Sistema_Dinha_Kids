package com.example.dinhakids.sistemaweb.exceptions;


public class PasswordEncodingException extends RuntimeException {

    public PasswordEncodingException(String message) {
        super(message);
    }

    public PasswordEncodingException(String message, Throwable cause) {
        super(message, cause);
    }
}
