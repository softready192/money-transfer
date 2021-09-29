package com.bsf.transfer.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccountExistsException.class)
    public ResponseEntity<?> globalAccountExistsExceptionHandler(AccountExistsException ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<?> globalInvalidAccountExceptionHandler(InvalidAccountException ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> globalInsufficientBalanceExceptionHandler(InsufficientBalanceException ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> globalMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {
        BindingResult result = ex.getBindingResult();
        List<String> fieldErrors = processFieldErrors(result.getFieldErrors());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), StringUtils.join(fieldErrors, "|"), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    private List<String> processFieldErrors(List<FieldError> fieldErrors) {
        List<String> errorList = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errorList.add(fieldError.getDefaultMessage());
        }
        return errorList;
    }
}
