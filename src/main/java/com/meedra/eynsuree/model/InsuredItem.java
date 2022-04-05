package com.meedra.eynsuree.model;

import com.meedra.eynsuree.enums.InsuranceStatus;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "insured_item")
public class InsuredItem extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insured_item_seq")
    @SequenceGenerator(name = "insured_item_seq")
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name= "insurance_premium")
    private Long insurancePremium;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "item_uid", updatable = false, nullable = false, length = 32, columnDefinition = "uuid")
    private final UUID uid = UUID.randomUUID();


    @Column(name = "insured_item_detail")
    private HashMap<String,String> insuredItem;



    @Enumerated(EnumType.STRING)
    @Column(name = "insurance_status", nullable = false)
    private InsuranceStatus insuranceStatus;


    @Column(name = "payment_date")
    private LocalDateTime paymentDate;


    @Column(name = "next_payment_date")
    private LocalDateTime nextPaymentDate;



}