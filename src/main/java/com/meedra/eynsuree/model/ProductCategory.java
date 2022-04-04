package com.meedra.eynsuree.model;

import com.meedra.eynsuree.enums.InsuranceCategory;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@ToString
@Table(name = "product_category")
public class ProductCategory extends AbstractAuditingEntity implements Serializable {


    private static final long serialVersionUID = -6163047415356862218L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;


    @Column(name = "product_cat_uid", updatable = false, nullable = false, length = 32, columnDefinition = "uuid")
    private final UUID uid = UUID.randomUUID();

    @Enumerated(EnumType.STRING)
    @Column(name = "product_cat_name",updatable = false, nullable = false, length = 32)
    private InsuranceCategory name;





    @OneToMany(targetEntity = Product.class,mappedBy = "productCategory", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> products;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductCategory that = (ProductCategory) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}