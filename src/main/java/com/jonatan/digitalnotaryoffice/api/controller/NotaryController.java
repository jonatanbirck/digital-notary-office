package com.jonatan.digitalnotaryoffice.api.controller;

import java.util.List;

import com.jonatan.digitalnotaryoffice.domain.entity.Notary;
import com.jonatan.digitalnotaryoffice.domain.services.NotaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotaryController {
    
    @Autowired
    private NotaryService notaryService;

    @GetMapping("/notaries")
    public String showNotaries(Model model) {
        List<Notary> notaries = notaryService.getNotaries();
        model.addAttribute("notaries", notaries);
        return "notaries";
    }
}
