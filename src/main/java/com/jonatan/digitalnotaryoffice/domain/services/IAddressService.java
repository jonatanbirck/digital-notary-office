package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;
import java.util.Optional;

import com.jonatan.digitalnotaryoffice.domain.entity.Address;

public interface IAddressService {

    Address saveAddress(Address address);

    Optional<Address> getAddress(Long id);

    List<Address> getAddresss();

    Address updateAddress(Address address);

    void deleteAddress(Address address);
}
