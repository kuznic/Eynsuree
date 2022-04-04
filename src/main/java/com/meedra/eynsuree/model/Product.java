package com.meedra.eynsuree.model;

import com.meedra.eynsuree.enums.InsuranceType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "product")
public class Product extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = -5170920267529701806L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;


    @Column(name = "product_uid", updatable = false, nullable = false, length = 32, columnDefinition = "uuid")
    private final UUID uid = UUID.randomUUID();




    @Enumerated(EnumType.STRING)
    @Column(name = "product_type",updatable = false, nullable = false, length = 32)
    private InsuranceType productType;


    @ManyToOne(optional = false)
    @JoinColumn(name = "product_cat_id", nullable = false)
    private ProductCategory productCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product products = (Product) o;
        return id != null && Objects.equals(id, products.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}