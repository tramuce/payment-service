package br.com.microservices.paymentservice.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    @ApiModelProperty(value = "HTTP Status Code Value")
    String code;
    @ApiModelProperty(value = "Reason Value")
    String message;
}
