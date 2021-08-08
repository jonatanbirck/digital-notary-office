package com.jonatan.digitalnotaryoffice.api.controller;

import java.util.List;
import java.util.Optional;

import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;
import com.jonatan.digitalnotaryoffice.domain.entity.CertificateState;
import com.jonatan.digitalnotaryoffice.domain.services.CertificateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/certificates")
public class CertificateController {
    
    @Autowired
    private CertificateService certificateService;

    @GetMapping
    public String showCertificates(Model model) {
        List<Certificate> certificates = certificateService.getCertificates();
        model.addAttribute("listCertificates", certificates);
        return "certificates";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("certificate", new Certificate());
        model.addAttribute("pageTitle", "Cadastrar certidão");
        return "certificate-form";
    }

    @PostMapping("/save")
    public String saveAddress(Certificate certificate, RedirectAttributes ra) {
        certificateService.saveCertificate(certificate);
        ra.addFlashAttribute("message","Certidão salva com sucesso.");
        return "redirect:/certificates";
    }

    @GetMapping("/edit/{id}")
    public String showEditFormm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        
        Optional<Certificate> certificate = certificateService.getCertificate(id);

        if( certificate.isPresent() ) {
            model.addAttribute("certificate", certificate.get() );
            model.addAttribute("pageTitle", "Editar certidão");
        } else {
            ra.addFlashAttribute("message","Certidão não encontrado.");
            return "redirect:/certificates";
        }
        
        return "certificate-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id, RedirectAttributes ra) {
        
        Optional<Certificate> certificate = certificateService.getCertificate(id);
        
        if( certificate.isPresent() ) {
            try {
                certificateService.deleteCertificate(certificate.get());
            } catch (Exception e) {
                ra.addFlashAttribute("message",e.getMessage());
            }
        } else {
            ra.addFlashAttribute("message","Certidão não encontrada.");
        }
        
        return "redirect:/certificates";
    }

}
