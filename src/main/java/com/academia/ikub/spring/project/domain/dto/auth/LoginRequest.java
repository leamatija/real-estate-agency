package com.academia.ikub.spring.project.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotNull
    @Email(message = "Email not valid")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
}
