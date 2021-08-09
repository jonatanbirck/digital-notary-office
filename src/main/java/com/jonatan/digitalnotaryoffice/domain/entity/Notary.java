package com.jonatan.digitalnotaryoffice.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notaries")
public class Notary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ref_address")
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "notaries_certificates",
        joinColumns = @JoinColumn(name = "ref_notary"),
        inverseJoinColumns = @JoinColumn(name = "ref_certificate")
    )
    private List<Certificate> certificates = new ArrayList<>();

}
