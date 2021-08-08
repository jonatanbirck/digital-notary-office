package com.jonatan.digitalnotaryoffice.domain.repository;

import com.jonatan.digitalnotaryoffice.domain.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
    
}
