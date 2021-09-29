package com.bsf.transfer.exception;

public class AccountExistsException extends RuntimeException{

    public AccountExistsException(String message) {
        super(message);
    }
}
