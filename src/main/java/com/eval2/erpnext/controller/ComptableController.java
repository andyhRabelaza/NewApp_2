package com.eval2.erpnext.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComptableController {

    @GetMapping("/facture")
    public String facturePartial(Model model) {

        return "fragments/facture :: content";
    }
}