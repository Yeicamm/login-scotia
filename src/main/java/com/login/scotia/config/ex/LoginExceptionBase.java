package com.login.scotia.config.ex;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class LoginExceptionBase extends RuntimeException{
    private final Error error;
    private final HttpStatus httpStatus;
    private final Error.Type type;

    protected LoginExceptionBase(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.type = getType();
        this.httpStatus = httpStatus;
    }
}
