package com.jonatan.digitalnotaryoffice.domain.services;

import java.util.List;
import java.util.Optional;

import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;

public interface ICertificateService {

    Certificate saveCertificate(Certificate certificate);

    Optional<Certificate> getCertificate(Long id);

    Optional<Certificate> getCertificate(String name);

    List<Certificate> getCertificates();

    Certificate updateCertificate(Certificate certificate);

    void deleteCertificate(Certificate certificate);    
}