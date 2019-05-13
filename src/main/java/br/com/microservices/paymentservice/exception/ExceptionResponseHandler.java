package br.com.microservices.paymentservice.exception;

import br.com.microservices.paymentservice.commons.ResourceBundleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice(basePackages = {"br.com.microservices.paymentservice.app"})
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<Object> handlerApiException(BaseException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                fillExceptionResponseObject(ex.getHttpStatus(), ex.getMessage(), ex.getMessageArgs()), new HttpHeaders(),
                ex.getHttpStatus(), request);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<Object> handlerNullPointerException(Exception ex, WebRequest request) {
        return handlerException(new NullPointerException("NullPointerException"), request);
    }

    @ExceptionHandler(value = {Exception.class})
    private ResponseEntity<Object> handlerException(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        return handleExceptionInternal(ex,
                fillExceptionResponseObject(HttpStatus.INTERNAL_SERVER_ERROR, "generic.exception",
                        new String[]{ex.getMessage()}),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        return handleExceptionInternal(ex, fillExceptionResponseObject(status, ex.getMessage(), null), headers, status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<ValidationErrorObject> errors = ex.getBindingResult().getFieldErrors().stream().map(
                error -> mountValidationExceptionObject(error)).collect(Collectors.toList());

        return handleExceptionInternal(ex,
                new ValidationExceptionResponse(ResourceBundleUtil.getString("generic.validation"), errors), headers,
                status, request);
    }


    private ValidationErrorObject mountValidationExceptionObject(FieldError fieldError) {

        ResourceBundle bundle = ResourceBundleUtil.getResourceBundle();

        String bundleStringMessage = fieldError.getDefaultMessage();

        String code = fieldError.getCodes()[0].split("\\.")[0];

        if (bundle.containsKey(code)) {
            bundleStringMessage = bundle.getString(code);
        }

        Object[] arguments = Arrays.copyOfRange(fieldError.getArguments(), 1, fieldError.getArguments().length);

        return new ValidationErrorObject(fieldError.getField(),
                MessageFormat.format(bundleStringMessage, arguments));
    }

    private ExceptionResponse fillExceptionResponseObject(HttpStatus httpStatus,
                                                          String messageCode,
                                                          String[] messageArgs) {
        String message = bundleGetStringMessage(messageCode);

        if (messageArgs != null && messageArgs.length > 0) {
            messageArgs = Arrays.stream(messageArgs).map(messageArg -> bundleGetStringMessage(messageArg)).toArray(
                    String[]::new);

            message = MessageFormat.format(message, messageArgs);
        }

        return new ExceptionResponse(httpStatus.toString(), message);
    }

    private String bundleGetStringMessage(String messageCode) {
        String message = messageCode;

        try {
            message = ResourceBundleUtil.getString(messageCode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return message;
    }
}
