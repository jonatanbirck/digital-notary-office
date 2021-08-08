package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;
import java.util.Optional;

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
    public Optional<Notary> getNotary(Long id) {
        return notaryRepository.findById(id);
    }

    @Override
    public Optional<Notary> getNotary(String name) {
        return notaryRepository.findByName(name);
    }

    @Override
    public List<Notary> getNotaries() {
        return notaryRepository.findAll();
    }

    @Override
    public Notary updateNotary(Notary notary) {
        return notaryRepository.save(notary);
    }

    @Override
    public void deleteNotary(Notary notary) {
        notaryRepository.delete(notary);
    }
    
}
