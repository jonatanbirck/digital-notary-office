package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;
import java.util.Optional;

import com.jonatan.digitalnotaryoffice.domain.entity.Notary;

public interface INotaryService {

    Notary saveNotary(Notary notary);

    Notary getNotary(Long id);

    Notary getNotary(String name);

    List<Notary> getNotaries();

    void deleteNotary(Notary notary);    
}
