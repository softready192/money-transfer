package com.bsf.transfer.exception;

public class InvalidAccountException extends RuntimeException{

    public InvalidAccountException(String message) {
        super(message);
    }
}
