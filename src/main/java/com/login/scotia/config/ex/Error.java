package com.login.scotia.config.ex;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error implements Serializable {

    private Type type;
    private String message;
    private List<String> detail;

    public enum Type {

        //404
        USER_NOT_FOUND_IN_DATA_BASE,


        // 409


        //422

        // 500
        PASSWORD_INCORRECT,
    }
}
