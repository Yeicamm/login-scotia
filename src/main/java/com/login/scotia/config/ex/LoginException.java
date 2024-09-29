package com.login.scotia.config.ex;

import org.springframework.http.HttpStatus;

public class LoginException extends LoginExceptionBase {

    public LoginException(String message) {
        super(Error.builder().type(Error.Type.PASSWORD_INCORRECT).message(message).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
