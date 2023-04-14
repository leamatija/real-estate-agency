package com.academia.ikub.spring.project.domain.dto.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @NotNull
    @Email(message = "Email not valid")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
}
