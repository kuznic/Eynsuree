package com.meedra.eynsuree.model;

import com.meedra.eynsuree.model.Authority;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = -6163047415356862218L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
    @SequenceGenerator(name = "authority_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_uid", updatable = false, nullable = false, length = 32, columnDefinition = "uuid")
    private final UUID uid = UUID.randomUUID();


    @Column(name = "first_name",updatable = false, nullable = false, length = 32)
    private String firstName;

    @Column(name = "last_name",updatable = false, nullable = false, length = 32)
    private String lastName;


    @Column(name = "email",updatable = false, nullable = false, length = 32)
    private String email;


    @Column(name = "password",updatable = false, nullable = false, length = 64)
    private String password;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Authority> authorities;


}