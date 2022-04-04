package com.meedra.eynsuree.model;

import com.meedra.eynsuree.model.Organization;
import com.meedra.eynsuree.model.Product;
import lombok.*;

import javax.persistence.*;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "insurance_factor")
public class InsuranceFactor extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_factor_seq")
    @SequenceGenerator(name = "insurance_factor_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private float factor;

}