package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;

import com.jonatan.digitalnotaryoffice.domain.entity.Notary;
import com.jonatan.digitalnotaryoffice.domain.repository.NotaryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NotaryService implements INotaryService {

    @Autowired
    private NotaryRepository notaryRepository;

    @Override
    public Notary saveNotary(Notary notary) {
        return notaryRepository.save(notary);
    }

    @Override
    public Notary getNotary(Long id) {
        return notaryRepository.getById(id);
    }

    @Override
    public Notary getNotary(String name) {
        return notaryRepository.findByName(name);
    }

    @Override
    public List<Notary> getNotaries() {
        return notaryRepository.findAll();
    }

    @Override
    public void deleteNotary(Notary notary) {
        notaryRepository.delete(notary);
    }
    
}
