package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "username must not be null")
    @NotEmpty(message = "username must not be empty")
    private String username;

    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    private String password;

}
