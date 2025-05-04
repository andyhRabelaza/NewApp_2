package com.eval2.erpnext.controller;

import com.eval2.erpnext.model.PurchaseInvoice;
import com.eval2.erpnext.service.FactureService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ComptableController {

    private final FactureService factureService;

    public ComptableController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping("/facture")
    public String facturePartial(Model model, HttpSession session) {
        List<PurchaseInvoice> factures = factureService.getAllPurchaseInvoices(session);
        model.addAttribute("factures", factures);
        return "fragments/facture :: content";
    }
}
