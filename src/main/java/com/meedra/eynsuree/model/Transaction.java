package com.meedra.eynsuree.model;

import com.meedra.eynsuree.enums.GenderType;
import com.meedra.eynsuree.enums.TransactionStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
@Table(name = "transaction")
public class Transaction extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq")
    @Column(name = "id", nullable = false)
    private Long id;



    @Column(name = "transaction_id", updatable = false,nullable = false,  length = 100)
    private String  transactionId;


    @Column(name = "beneficiary_name")
    private String beneficiaryName;


    @Column(name = "customer_id")
    private String customerID;

    @Column(name = "transaction_amount")
    private Long transactionAmount;


    @Column(name = "payer_reference")
    private String payerReference;


    @Column(name = "external_reference")
    private String externalReference;

    @Column(name = "transaction_date_time")
    private LocalDateTime transactionDateTime = LocalDateTime.now();


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;
}