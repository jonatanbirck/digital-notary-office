package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;
import java.util.Optional;

import com.jonatan.digitalnotaryoffice.domain.entity.Notary;

public interface INotaryService {

    Notary saveNotary(Notary notary);

    Optional<Notary> getNotary(Long id);

    Optional<Notary> getNotary(String name);

    List<Notary> getNotaries();

    Notary updateNotary(Notary notary);

    void deleteNotary(Notary notary);    
}
