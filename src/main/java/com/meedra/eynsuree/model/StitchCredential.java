package com.meedra.eynsuree.model;

import com.meedra.eynsuree.model.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Entity
@Table(name = "stitch_credential")
@AllArgsConstructor
@NoArgsConstructor
public class StitchCredential extends AbstractAuditingEntity {
    @Id
    @Column(name = "client_id", nullable = false)
    private String id;


    @Column(name = "scopes", length = 600)
    private ArrayList<String> scopes;


    @Column(name = "redirect_urls")
    private ArrayList<String> redirectUrls;

    @Column(name = "grant_types")
    private ArrayList<String> grantTypes;
}