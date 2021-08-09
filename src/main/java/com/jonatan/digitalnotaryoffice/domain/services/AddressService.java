package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;
import java.util.Optional;

import com.jonatan.digitalnotaryoffice.domain.entity.Address;
import com.jonatan.digitalnotaryoffice.domain.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getAddress(Long id) {
        return addressRepository.getById(id);
    }

    @Override
    public List<Address> getAdresses() {
        return addressRepository.findAll();
    }

    @Override
    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }
    
}
