package com.meedra.eynsuree.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Table(name = "organization")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Organization extends AbstractAuditingEntity implements Serializable {


    private static final long serialVersionUID = -5170920267523701806L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;


    @Column(name = "org_uid", updatable = false, nullable = false, length = 32, columnDefinition = "uuid")
    private final UUID  uid = UUID.randomUUID();


    @Column(name = "name",updatable = false, nullable = false, length = 32)
    private String name;

    @Column(name = "company_address", nullable = false, length = 32)
    private String address;

    @Column(name = "company_registration_number",updatable = false, nullable = false)
    private String regNumber;

    @OneToMany( targetEntity = Customer.class,mappedBy = "organization", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Customer> customers ;

    @Column(name = "bank_id",updatable = false, nullable = false, length = 32)
    private String bankId;

    @Column(name = "bank_account_number",updatable = false, nullable = false, length = 32)
    private String bankAccountNumber;



}