package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;
import java.util.Optional;

import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;
import com.jonatan.digitalnotaryoffice.domain.repository.CertificateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CertificateService implements ICertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public Certificate saveCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate getCertificate(Long id) {
        return certificateRepository.getById(id);
    }

    @Override
    public Certificate getCertificate(String name) {
        return certificateRepository.findByName(name);
    }

    @Override
    public List<Certificate> getCertificates() {
        return certificateRepository.findAll();
    }

    @Override
    public void deleteCertificate(Certificate certificate) {
        certificateRepository.delete(certificate);
    }
    
}
