package com.login.scotia.config.ex;

import org.springframework.http.HttpStatus;

public class UserNotFoundInDataBase extends LoginExceptionBase {
    public UserNotFoundInDataBase(String message){
        super(Error.builder()
                .type(Error.Type.USER_NOT_FOUND_IN_DATA_BASE)
                .message(message)
                .build(), HttpStatus.NOT_FOUND);
    }
}
