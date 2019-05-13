package br.com.microservices.paymentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrorObject {
    private String field;
    private String message;
}
