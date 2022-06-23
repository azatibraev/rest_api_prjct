package com.example.company_rest_api.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter
public class ExceptionResponse {

        private HttpStatus status;
        private String exceptionClassName;
        private  String message;

        public ExceptionResponse() {
        }

        public ExceptionResponse(HttpStatus status, String exceptionClassName, String message) {
            this.status = status;
            this.exceptionClassName = exceptionClassName;
            this.message = message;
        }
}
