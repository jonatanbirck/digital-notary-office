package com.jonatan.digitalnotaryoffice.domain.repository;

import com.jonatan.digitalnotaryoffice.domain.entity.Notary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaryRepository extends JpaRepository<Notary,Long> {
 
    Notary findByName(String name);

}
