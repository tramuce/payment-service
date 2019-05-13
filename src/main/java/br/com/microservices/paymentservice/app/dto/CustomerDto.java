package br.com.microservices.paymentservice.app.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerDto {
    @NotNull
    private String address;
    @NotNull
    private String addressComplement;
    @NotNull
    private String addressNumber;
    @NotNull
    private String cityName;
    @NotNull
    private String cnpjCpf;
    @NotNull
    private String email;
    @NotNull
    private String neighborhood;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String state;
    @NotNull
    private String zipCode;
}
