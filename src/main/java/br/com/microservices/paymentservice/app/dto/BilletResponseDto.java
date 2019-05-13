package br.com.microservices.paymentservice.app.dto;

import br.com.microservices.paymentservice.app.model.BilletStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BilletResponseDto extends BilletRequestDto {
    @NotNull
    private BilletStatusEnum status;
    private LocalDateTime paidAt;
    private Double paidAmount;
}
