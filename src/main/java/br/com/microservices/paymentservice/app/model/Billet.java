package br.com.microservices.paymentservice.app.model;

import br.com.microservices.paymentservice.app.dto.PaymentType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_billet")
@Data
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double amount;
    @Column
    private String description;
    @Column
    private LocalDateTime expireAt;
    @Column
    private Double fineValue;
    @Column
    private Double discountValue;
    @Column
    @Enumerated(EnumType.STRING)
    private BilletStatusEnum status;
    @Column
    private LocalDateTime paidAt;
    @Column
    private Double paidAmount;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
