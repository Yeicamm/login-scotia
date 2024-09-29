package com.login.scotia.config.ex;

import org.springframework.http.HttpStatus;

public class UserNotRegisteredException extends LoginExceptionBase {
    public UserNotRegisteredException(String message){
        super(Error.builder()
                .type(Error.Type.PASSWORD_INCORRECT)
                .message(message)
                .build(), HttpStatus.UNAUTHORIZED);
    }
}