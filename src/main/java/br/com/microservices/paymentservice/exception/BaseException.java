package br.com.microservices.paymentservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    private HttpStatus httpStatus;
    private String[] messageArgs;

    public BaseException(HttpStatus httpStatus, String message, String... messageArgs) {
        super(message);
        this.httpStatus = httpStatus;
        this.messageArgs = messageArgs;
    }
}
