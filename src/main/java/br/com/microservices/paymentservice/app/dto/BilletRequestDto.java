package br.com.microservices.paymentservice.app.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BilletRequestDto {
    @NotNull
    private Double amount;
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime expireAt;
    private Double fineValue;
    private Double discountValue;
    @NotNull
    private CustomerDto customer;
}
