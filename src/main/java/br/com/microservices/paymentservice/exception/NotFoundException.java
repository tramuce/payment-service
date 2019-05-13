package br.com.microservices.paymentservice.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message, String... messageArgs) {
        super(HttpStatus.NOT_FOUND, message, messageArgs);
    }
}
