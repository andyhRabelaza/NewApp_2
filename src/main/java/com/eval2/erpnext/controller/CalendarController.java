package com.eval2.erpnext.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eval2.erpnext.model.PurchaseInvoice;
import com.eval2.erpnext.service.FactureService;
import com.eval2.erpnext.service.PaymentEntryService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class CalendarController {

    private final FactureService factureService;

    public CalendarController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping("/calendrier")
    public String Showcalendrier() {
        return "fragments/calendrier :: content";
    }

    @GetMapping("/api/purchase-invoices")
    @ResponseBody
    public List<PurchaseInvoice> getPurchaseInvoices(HttpSession session) {
        return factureService.getAllPurchaseInvoices(session); // remplace par ton vrai nom de service
    }
}
