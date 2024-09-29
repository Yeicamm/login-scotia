package com.login.scotia.config.ex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(value = {UserNotRegisteredException.class})
    public ResponseEntity<ErrorResponse> userNotFoundInKeycloakEx(UserNotRegisteredException ex) {
        log.error("User not found in the system: {}", ex.getError().getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getError().getType(), ex.getError().getMessage()), ex.getHttpStatus());
    }


    @ExceptionHandler(value = {UserNotFoundInDataBase.class})
    public ResponseEntity<ErrorResponse> handlerUserNotFoundInDataBaseEx(UserNotFoundInDataBase exception) {
        log.error("User not found in data base: {}", exception.getError().getMessage());
        return new ResponseEntity<>(new ErrorResponse(exception.getError().getType(), exception.getError().getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {LoginException.class})
    protected ResponseEntity<ErrorResponse> handlerLoginEx(final LoginException ex) {
        log.error("Error in login: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getError().getType()), ex.getHttpStatus());
    }
    /*
    @Nullable
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        MessageResponse response = new MessageResponse();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        response.setCodeStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setMessage("Validation error");
        response.setObject(errors);
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }*/
}
