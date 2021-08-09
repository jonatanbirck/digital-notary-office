package com.jonatan.digitalnotaryoffice.domain.repository;

import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate,Long> {
    
    Certificate findByName(String name);
    
}
