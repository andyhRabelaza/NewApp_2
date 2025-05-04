package com.eval2.erpnext.controller;

import org.springframework.stereotype.Controller; // Ajoute cette importation
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Assure-toi que cette annotation est présente
public class FournisseurController {

    @GetMapping("/liste-demande")
    public String listeDemandeParFournisseur(Model model) {
        return "fragments/listedemande :: content";
    }

    @GetMapping("/liste-commande")
    public String listeCommande(Model model) {
        return "fragments/listecommande :: content";
    }
}
