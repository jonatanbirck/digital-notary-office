package com.jonatan.digitalnotaryoffice.domain.repository;

import java.util.Optional;

import com.jonatan.digitalnotaryoffice.domain.entity.Notary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaryRepository extends JpaRepository<Notary,Long> {
 
    Optional<Notary> findByName(String name);

}
