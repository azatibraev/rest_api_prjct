package com.example.company_rest_api.handler;

import com.example.company_rest_api.exceptions.*;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

/**
 * @author Azat Ibraev
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CompanyNotFoundException.class, CourseNotFoundException.class,
            GroupNotFoundException.class, StudentNotFoundException.class, TeacherNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerBookNotFoundException(NotFoundException e) {
        return  new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage());
    }
}
