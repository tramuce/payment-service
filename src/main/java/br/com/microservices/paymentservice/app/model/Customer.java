package br.com.microservices.paymentservice.app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String address;
    @Column
    private String addressComplement;
    @Column
    private String addressNumber;
    @Column
    private String cityName;
    @Column
    private String cnpjCpf;
    @Column
    private String email;
    @Column
    private String neighborhood;
    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column
    private String state;
    @Column
    private String zipCode;
}
