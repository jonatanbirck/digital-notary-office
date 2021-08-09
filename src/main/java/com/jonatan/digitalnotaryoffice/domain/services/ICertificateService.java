package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;
import java.util.Optional;

import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;

public interface ICertificateService {

    Certificate saveCertificate(Certificate certificate);

    Certificate getCertificate(Long id);

    Certificate getCertificate(String name);

    List<Certificate> getCertificates();

    void deleteCertificate(Certificate certificate);    
}