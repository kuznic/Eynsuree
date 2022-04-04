package com.meedra.eynsuree.model;


import com.meedra.eynsuree.enums.GenderType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
@Table(name = "org_customer")
public class Customer extends AbstractAuditingEntity implements Serializable {


    private static final long serialVersionUID = -2966158156468908215L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "customer_uid", updatable = false, nullable = false, length = 32, columnDefinition = "uuid")
    private final UUID uid = UUID.randomUUID();

    @ManyToOne(optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;


    @Column(name = "first_name",updatable = false, nullable = false, length = 32)
    private String firstName;


    @Column(name = "last_name",updatable = false, nullable = false, length = 32)
    private String LastName;

    @Column(name = "bvn")
    private String bvn;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private GenderType gender;


    @Column(name = "email",updatable = false, nullable = false, length = 32)
    private String email;



}