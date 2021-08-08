package com.jonatan.digitalnotaryoffice.api.controller;

import java.util.List;

import com.jonatan.digitalnotaryoffice.domain.entity.Address;
import com.jonatan.digitalnotaryoffice.domain.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {
    
    @Autowired
    private AddressService addressService;

    @GetMapping("/adresses")
    public String showAdresses(Model model) {
        List<Address> adresses = addressService.getAddresss();
        model.addAttribute("adresses", adresses);
        return "adresses";
    }

}
