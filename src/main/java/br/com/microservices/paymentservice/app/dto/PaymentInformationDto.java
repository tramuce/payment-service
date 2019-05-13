package br.com.microservices.paymentservice.app.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PaymentInformationDto {
    @NotNull
    private LocalDateTime paidAt;
    @NotNull
    private Double paidAmount;
    @NotNull
    private PaymentType paymentType;
}
