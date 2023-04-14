package com.academia.ikub.spring.project.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO {
    private Integer id;
    @NotNull(message = "Firstname is required")
    @NotEmpty(message = "This field can not be empty")
    private String firstname;
    @NotNull(message = "Lastname is required")
    @NotEmpty(message = "This field can not be empty")
    private String lastname;
    @Email(message = "Email is not valid")
    private String email;
    @NotNull(message = "Phone number is required")
    private String phoneNumber;

}
