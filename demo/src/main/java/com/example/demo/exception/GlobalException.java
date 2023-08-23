package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest webRequest
    ){
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                new Date()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorObject> handleValidationException(
            ValidationException ex,
            WebRequest request
    ) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                new Date()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorObject> handleUnAuthorizedException(
            UnauthorizedException ex,
            WebRequest request
    ) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                new Date()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }
/*
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorObject> handleBadCredentialsException(
            BadCredentialsException ex,
            WebRequest request
    ) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                new Date()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }
*/

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorObject> handleInternalServiceException(
            InternalServerErrorException ex,
            WebRequest request
    ) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                new Date()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorObject> handForbiddenException(
            ForbiddenException ex,
            WebRequest request
    ) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                new Date()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ErrorObject> handleDataIntegrityException(
            DataIntegrityException ex,
            WebRequest request
    ) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                new Date()
        );

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

}
