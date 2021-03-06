package com.jonatan.digitalnotaryoffice.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "certificates")
public class Certificate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(nullable = false)
    private String version = "1.0";
    
    @Column(nullable = false)
    private CertificateState state = CertificateState.ACTIVE;

    @ManyToMany(mappedBy = "certificates")
    private List<Notary> notaries = new ArrayList<>();
}
