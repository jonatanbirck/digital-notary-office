package com.jonatan.digitalnotaryoffice.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jonatan.digitalnotaryoffice.api.model.NotaryDTO;
import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;
import com.jonatan.digitalnotaryoffice.domain.entity.Notary;
import com.jonatan.digitalnotaryoffice.domain.services.AddressService;
import com.jonatan.digitalnotaryoffice.domain.services.CertificateService;
import com.jonatan.digitalnotaryoffice.domain.services.NotaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notaries")
public class NotaryController {
    
    @Autowired
    private NotaryService notaryService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CertificateService certificateService;

    @GetMapping
    public String showNotaries(Model model) {
        List<Notary> notaries = notaryService.getNotaries();
        model.addAttribute("listNotaries", notaries);
        return "notaries";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("notaryDTO", new NotaryDTO());
        model.addAttribute("pageTitle", "Cadastrar cartório");
        model.addAttribute("listAdresses", addressService.getAddresss());
        model.addAttribute("listCertificates", certificateService.getCertificates());
        return "notary-form";
    }    

    @PostMapping("/save")
    public String saveAddress(NotaryDTO notaryDTO, RedirectAttributes ra) {

        try {
            Notary notary = new Notary();
            
            if( notaryDTO.getId() != null )
                notary.setId(notaryDTO.getId());
            
            notary.setName(notaryDTO.getName());
            notary.setAddress(addressService.getAddress(notaryDTO.getRefAddress()).get());
            List<Certificate> certificates = new ArrayList<>();
            for(Long refCertificate : notaryDTO.getCertificates() ){
                certificates.add(certificateService.getCertificate(refCertificate).get());
            }
            notary.setCertificates(certificates);
    
            notaryService.saveNotary(notary);
            ra.addFlashAttribute("message","Cartório salvo com sucesso.");
        } catch( Exception e ) {
            ra.addFlashAttribute("message",e.getMessage());
        }

        return "redirect:/notaries";
    }

    @GetMapping("/edit/{id}")
    public String showEditFormm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        
        Optional<Notary> notary = notaryService.getNotary(id);

        NotaryDTO notaryDTO = new NotaryDTO();
        notaryDTO.setId(id);
        notaryDTO.setName(notary.get().getName());
        notaryDTO.setRefAddress(notary.get().getAddress().getId());
        List<Long> certificates = new ArrayList();
        for( Certificate certificate : notary.get().getCertificates() ) {
            certificates.add(certificate.getId());
        }

        if( notary.isPresent() ) {
            model.addAttribute("notaryDTO", notaryDTO );
            model.addAttribute("pageTitle", "Editar cartório");
            model.addAttribute("listAdresses", addressService.getAddresss());
            model.addAttribute("listCertificates", certificateService.getCertificates());
        } else {
            ra.addFlashAttribute("message","Cartório não encontrado.");
            return "redirect:/notaries";
        }
        
        return "notary-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id, RedirectAttributes ra) {
        
        Optional<Notary> notary = notaryService.getNotary(id);
        
        if( notary.isPresent() ) {
            try {
                notaryService.deleteNotary(notary.get());
            } catch (Exception e) {
                ra.addFlashAttribute("message",e.getMessage());
            }
        } else {
            ra.addFlashAttribute("message","Cartório não encontrado.");
        }
        
        return "redirect:/notaries";
    }
}
