package com.eval2.erpnext.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;
import com.eval2.erpnext.model.Commande;
import com.eval2.erpnext.model.CommandeResponse;

import java.util.List;

@Service
public class CommandeService {

    @Value("${erpnext.api.url}")
    private String erpnextApiUrl;

    private final RestTemplate restTemplate;

    public CommandeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Commande> getCommandes(HttpSession session) {
        String sid = (String) session.getAttribute("sid");

        if (sid == null) {
            throw new RuntimeException("Aucune session active. Veuillez vous reconnecter.");
        }

        // URL avec le paramètre 'fields' ajouté
        String url = erpnextApiUrl
                + "/api/resource/Purchase Order?fields=[\"*\"]";

        // Ajout du SID dans les headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, "sid=" + sid);

        // Création de l'entité HTTP avec les headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Requête GET avec RestTemplate
            ResponseEntity<CommandeResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, CommandeResponse.class);

            // Retourner les commandes depuis la réponse
            return response.getBody().getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des commandes.");
        }
    }
}
