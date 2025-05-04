package com.eval2.erpnext.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;
import com.eval2.erpnext.model.Fournisseur;
import com.eval2.erpnext.model.FournisseurResponse;
import com.eval2.erpnext.model.SupplierQuotation;
import com.eval2.erpnext.model.SupplierQuotationResponse;

import java.util.List;

@Service
public class FournisseurService {

    @Value("${erpnext.api.url}")
    private String erpnextApiUrl;

    private final RestTemplate restTemplate;

    public FournisseurService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Fournisseur> getFournisseurs(HttpSession session) {
        String sid = (String) session.getAttribute("sid");

        if (sid == null) {
            throw new RuntimeException("Aucune session active. Veuillez vous reconnecter.");
        }

        String url = erpnextApiUrl + "/api/resource/Supplier";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, "sid=" + sid);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<FournisseurResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, FournisseurResponse.class);

            return response.getBody().getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des fournisseurs.");
        }
    }

    public List<SupplierQuotation> getSupplierQuotations(HttpSession session, String parametre1) {
        // Récupérer le SID de la session
        String sid = (String) session.getAttribute("sid");

        if (sid == null) {
            throw new RuntimeException("Aucune session active. Veuillez vous reconnecter.");
        }

        // URL de l'API ERPNext pour les quotations de fournisseurs
        String url = erpnextApiUrl + "/api/resource/Supplier Quotation?fields=[\"*\"]&filters=[[\"supplier\",\"=\",\""
                + parametre1 + "\"]]&sid=" + sid;

        // Création des en-têtes HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, "sid=" + sid);

        // Préparer l'entité avec les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Faire l'appel à l'API et récupérer la réponse
            ResponseEntity<SupplierQuotationResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, SupplierQuotationResponse.class);

            // Retourner les données extraites de la réponse
            return response.getBody().getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des quotations de fournisseur.");
        }
    }

}
