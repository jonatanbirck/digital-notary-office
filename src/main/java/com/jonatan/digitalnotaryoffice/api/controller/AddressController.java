package com.jonatan.digitalnotaryoffice.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;

import com.jonatan.digitalnotaryoffice.domain.entity.Address;
import com.jonatan.digitalnotaryoffice.domain.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/adresses")
public class AddressController {
    
    @Autowired
    private AddressService addressService;

    @GetMapping
    public String showAdresses(Model model) {
        List<Address> adresses = addressService.getAddresss();
        model.addAttribute("listAdresses", adresses);
        return "adresses";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("pageTitle", "Cadastrar endereço");
        return "address-form";
    }

    @PostMapping("/save")
    public String saveAddress(Address address, RedirectAttributes ra) {
        addressService.saveAddress(address);
        ra.addFlashAttribute("message","Endereço salvo com sucesso.");
        return "redirect:/adresses";
    }

    @GetMapping("/edit/{id}")
    public String showEditFormm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        
        Optional<Address> address = addressService.getAddress(id);

        if( address.isPresent() ) {
            model.addAttribute("address", address.get() );
            model.addAttribute("pageTitle", "Editar endereço");
        } else {
            ra.addFlashAttribute("message","Endereço não encontrado.");
            return "redirect:/adresses";
        }
        
        return "address-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id, RedirectAttributes ra) {
        
        Optional<Address> address = addressService.getAddress(id);
        
        if( address.isPresent() ) {
            try {
                addressService.deleteAddress(address.get());
            } catch (Exception e) {
                ra.addFlashAttribute("message",e.getMessage());
            }
        } else {
            ra.addFlashAttribute("message","Endereço não encontrado.");
        }
        
        return "redirect:/adresses";
    }

}
