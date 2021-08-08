package com.jonatan.digitalnotaryoffice.api.controller;

import java.util.List;

import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;
import com.jonatan.digitalnotaryoffice.domain.services.CertificateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CertificateController {
    
    @Autowired
    private CertificateService certificateService;

    @GetMapping("/certificates")
    public String showCertificates(Model model) {
        List<Certificate> certificates = certificateService.getCertificates();
        model.addAttribute("certificates", certificates);
        return "certificates";
    }

}
