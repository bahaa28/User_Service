package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ErrorObject {

    private final int httpStatus;
    private final String message;
    private final Date timeStamp;

}
