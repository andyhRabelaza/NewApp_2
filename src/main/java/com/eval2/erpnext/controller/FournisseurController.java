package com.eval2.erpnext.controller;

import com.eval2.erpnext.model.Commande;
import com.eval2.erpnext.model.Fournisseur;
import com.eval2.erpnext.model.SupplierQuotation;
import com.eval2.erpnext.service.FournisseurService;
import com.eval2.erpnext.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService; // Injection du service FournisseurService

    @Autowired
    private CommandeService commandeService;

    @GetMapping("/liste-demande")
    public String listeDemandeParFournisseur(HttpSession session, Model model) {
        List<Fournisseur> fournisseurs = fournisseurService.getFournisseurs(session);
        model.addAttribute("fournisseurs", fournisseurs);
        return "fragments/listedemande :: content";
    }

    @GetMapping("/demande/{fournisseur}")
    @ResponseBody
    public List<SupplierQuotation> getDemandeParFournisseur(@PathVariable String fournisseur, HttpSession session) {
        return fournisseurService.getSupplierQuotations(session, fournisseur);
    }

    @GetMapping("/liste-commande")
    public String listeCommande(HttpSession session, Model model) {
        List<Commande> commandes = commandeService.getCommandes(session);
        model.addAttribute("commandes", commandes);
        return "fragments/listecommande :: content";
    }
}
