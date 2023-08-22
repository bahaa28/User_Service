package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegesterDto {

    @NotNull(message = "username must not be null")
    @NotEmpty(message = "username must not be empty")
    private String username;

    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    private String password;

    @NotNull(message = "first name must not be null")
    @NotEmpty(message = "first name must not be empty")
    private String firstName;

    @NotNull(message = "last name must not be null")
    @NotEmpty(message = "last name must not be empty")
    private String lastName;

    @NotNull(message = "birth date must not be null")
    private Date birthDate;
}
