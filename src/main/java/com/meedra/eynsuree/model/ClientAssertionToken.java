package com.meedra.eynsuree.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "client_assertion_token")
public class ClientAssertionToken extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_assertion_token_seq")
    @SequenceGenerator(name = "client_assertion_token_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="assertion_token", length = 3000)
    private String assertionToken;

}