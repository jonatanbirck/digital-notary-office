package com.jonatan.digitalnotaryoffice.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.jonatan.digitalnotaryoffice.api.model.NotaryDTO;
import com.jonatan.digitalnotaryoffice.domain.entity.Address;
import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;
import com.jonatan.digitalnotaryoffice.domain.entity.Notary;
import com.jonatan.digitalnotaryoffice.domain.services.AddressService;
import com.jonatan.digitalnotaryoffice.domain.services.CertificateService;
import com.jonatan.digitalnotaryoffice.domain.services.NotaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("listAddresses", addressService.getAdresses());
        model.addAttribute("listCertificates", certificateService.getCertificates());
        return "notary-form";
    }    

    @PostMapping("/save")
    public String saveAddress(@Valid NotaryDTO notaryDTO, BindingResult result, Model model, RedirectAttributes ra) {

        try {
            if( result.hasErrors() ) {
                model.addAttribute("listAddresses", addressService.getAdresses());
                model.addAttribute("listCertificates", certificateService.getCertificates());
                return "notary-form";
            }

            Notary notary = toNotary(notaryDTO);

            notaryService.saveNotary(notary);
            
            ra.addFlashAttribute("message","Cartório salvo com sucesso.");
        }catch(Exception e) {
            ra.addFlashAttribute("message",e.getMessage());
        }

        return "redirect:/notaries";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        
        try {
            Notary notary = notaryService.getNotary(id);

            if( notary != null ) {
                NotaryDTO notaryDTO = toNotaryDTO(notary);
                model.addAttribute("notaryDTO", notaryDTO );
                model.addAttribute("listAddresses", addressService.getAdresses());
                model.addAttribute("listCertificates", certificateService.getCertificates());
            } else {
                ra.addFlashAttribute("message","Cartório não encontrado.");
                return "redirect:/notaries";
            }
        }catch (Exception e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        
        return "notary-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id, RedirectAttributes ra) {
        
        Notary notary = notaryService.getNotary(id);
        
        if( notary != null ) {
            try {
                notaryService.deleteNotary(notary);
            } catch (Exception e) {
                ra.addFlashAttribute("message",e.getMessage());
            }
        } else {
            ra.addFlashAttribute("message","Cartório não encontrado.");
        }
        
        return "redirect:/notaries";
    }

    private NotaryDTO toNotaryDTO(Notary notary) {
        Address address = notary.getAddress();
        List<Certificate> certificates = notary.getCertificates();

        List<Long> certificatesIds = new ArrayList<>();
        
        for( Certificate certificate : certificates )
            certificatesIds.add(certificate.getId());
        
        NotaryDTO notaryDTO = new NotaryDTO();
        notaryDTO.setId(notary.getId());
        notaryDTO.setName(notary.getName());
        notaryDTO.setRefAddress(address == null ? null : address.getId());
        notaryDTO.setCertificates(certificatesIds);
        return notaryDTO;
    }

    private Notary toNotary(NotaryDTO notaryDTO) {
        List<Certificate> certificates = new ArrayList<>();

        for(Long refCertificate : notaryDTO.getCertificates())
            certificates.add(certificateService.getCertificate(refCertificate));

        Notary notary = new Notary();
        notary.setId(notaryDTO.getId());
        notary.setName(notaryDTO.getName());
        notary.setAddress(addressService.getAddress(notaryDTO.getRefAddress()));
        notary.setCertificates(certificates);

        return notary;
    }


}
