package com.eval2.erpnext.controller;

import com.eval2.erpnext.model.PaymentEntry;
import com.eval2.erpnext.model.PurchaseInvoice;
import com.eval2.erpnext.service.FactureService;
import com.eval2.erpnext.service.PaymentEntryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ComptableController {

    private final FactureService factureService;
    private final PaymentEntryService paymentEntryService;

    public ComptableController(FactureService factureService, PaymentEntryService paymentEntryService) {
        this.factureService = factureService;
        this.paymentEntryService = paymentEntryService;
    }

    @GetMapping("/facture")
    public String facturePartial(Model model, HttpSession session) {
        List<PurchaseInvoice> factures = factureService.getAllPurchaseInvoices(session);
        model.addAttribute("factures", factures);
        return "fragments/facture :: content";
    }

    @PostMapping("/facture/paiement")
    @ResponseBody
    public ResponseEntity<String> createPayment(@RequestBody PaymentEntry paymentEntry, HttpSession session) {
        try {
            String response = paymentEntryService.createPaymentEntry(session, paymentEntry);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la création du paiement : " + e.getMessage());
        }
    }
}
