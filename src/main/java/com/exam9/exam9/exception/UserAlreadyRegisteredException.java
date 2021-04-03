package com.exam9.exam9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserAlreadyRegisteredException extends RuntimeException {
    private String resource;
    private int id;
}
