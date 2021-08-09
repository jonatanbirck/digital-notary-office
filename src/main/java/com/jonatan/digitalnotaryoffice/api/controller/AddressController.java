package com.jonatan.digitalnotaryoffice.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.jonatan.digitalnotaryoffice.api.model.AddressDTO;
import com.jonatan.digitalnotaryoffice.domain.entity.Address;
import com.jonatan.digitalnotaryoffice.domain.services.AddressService;

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
@RequestMapping("/adresses")
public class AddressController {
    
    @Autowired
    private AddressService addressService;

    @GetMapping
    public String showAdresses(Model model) {
        List<Address> adresses = addressService.getAdresses();
        model.addAttribute("listAdresses", adresses);
        return "adresses";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("addressDTO", new AddressDTO());
        return "address-form";
    }

    @PostMapping("/save")
    public String saveAddress(@Valid AddressDTO addressDTO, BindingResult result, Model model, RedirectAttributes ra) {

        try {
            if( result.hasErrors() ) {
                return "address-form";
            }
            
            Address address = toAddress(addressDTO);

            addressService.saveAddress(address);
            
            ra.addFlashAttribute("message","Endereço salvo com sucesso.");
        } catch (Exception e) {
            ra.addFlashAttribute("message",e.getMessage());
        }

        return "redirect:/adresses";
    }

    @GetMapping("/edit/{id}")
    public String showEditFormm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        
        try {
            Address address = addressService.getAddress(id);

            if( address != null ) {
                AddressDTO addressDTO = toAddressDTO(address);
                model.addAttribute("addressDTO", addressDTO );
            } else {
                ra.addFlashAttribute("message","Endereço não encontrado.");
                return "redirect:/adresses";
            }
        } catch (Exception e) {
            ra.addFlashAttribute("message",e.getMessage());
        }

        return "address-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id, RedirectAttributes ra) {
        
        Address address = addressService.getAddress(id);
        
        if( address != null ) {
            try {
                addressService.deleteAddress(address);
            } catch (Exception e) {
                ra.addFlashAttribute("message",e.getMessage());
            }
        } else {
            ra.addFlashAttribute("message","Endereço não encontrado.");
        }
        
        return "redirect:/adresses";
    }

    private AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCep(address.getCep());
        addressDTO.setCity(address.getCity());
        addressDTO.setComplement(address.getComplement());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setId(address.getId());
        addressDTO.setState(address.getState());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setUf(address.getUf());
        return addressDTO;
    }

    private Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setCep(addressDTO.getCep());
        address.setCity(addressDTO.getCity());
        address.setComplement(addressDTO.getComplement());
        address.setDistrict(addressDTO.getComplement());
        address.setId(addressDTO.getId());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setUf(addressDTO.getUf());
        return address;
    }
}
