package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;

import com.jonatan.digitalnotaryoffice.domain.entity.Address;

public interface IAddressService {

    Address saveAddress(Address address);

    Address getAddress(Long id);

    List<Address> getAdresses();

    void deleteAddress(Address address);
}
