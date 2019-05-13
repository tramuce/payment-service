package br.com.microservices.paymentservice.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(String message, String... messageArgs) {
        super(HttpStatus.BAD_REQUEST, message, messageArgs);
    }
}
