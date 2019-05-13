package br.com.microservices.paymentservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ValidationExceptionResponse extends ExceptionResponse {
    List<ValidationErrorObject> errors;

    public ValidationExceptionResponse(String message, List<ValidationErrorObject> errors) {
        super(HttpStatus.BAD_REQUEST.toString(), message);
        this.errors = errors;
    }
}
