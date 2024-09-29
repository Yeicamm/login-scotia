package com.login.scotia.dto;

import com.login.scotia.config.enums.TypeContractEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUserDTO {
    private String name;
    private String identification;
    private String email;
    private String program;
    private TypeContractEnum typeContract;
}
