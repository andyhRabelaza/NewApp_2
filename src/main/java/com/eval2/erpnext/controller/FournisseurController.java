package com.eval2.erpnext.controller;

import com.eval2.erpnext.model.Commande;
import com.eval2.erpnext.model.Fournisseur;
import com.eval2.erpnext.model.SupplierQuotation;
import com.eval2.erpnext.model.SupplierQuotationUpdateRequest;
import com.eval2.erpnext.service.FournisseurService;
import com.eval2.erpnext.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/demande/detail/{quotationId}")
    @ResponseBody
    public SupplierQuotation getDetailDemande(@PathVariable String quotationId, HttpSession session) {
        return fournisseurService.getSupplierQuotationById(session, quotationId);
    }

    @PutMapping("/demande/details/{quotationId}")
    @ResponseBody
    public SupplierQuotation updateDemande(@PathVariable String quotationId, HttpSession session,
            @RequestBody SupplierQuotationUpdateRequest updatedItems) {
        // Log du côté serveur pour afficher les données reçues
        System.out.println("Mise à jour de la demande avec ID : " + quotationId);
        System.out.println("Détails reçus : " + updatedItems.toString());

        // Traitement de la mise à jour de la demande
        SupplierQuotation updatedQuotation = fournisseurService.updateSupplierQuotation(quotationId, session,
                updatedItems);

        // Retourne l'objet mis à jour
        return updatedQuotation;
    }

    // @PostMapping("/demande/submit/{quotationId}")
    // @ResponseBody
    // public ResponseEntity<String> submitDemande(@PathVariable String quotationId,
    // HttpSession session) {
    // try {
    // fournisseurService.submitSupplierQuotation(quotationId, session);
    // return ResponseEntity.ok("Demande soumise avec succès.");
    // } catch (Exception e) {
    // e.printStackTrace();
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur
    // lors de la soumission.");
    // }
    // }

}
