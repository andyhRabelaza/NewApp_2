package com.eval2.erpnext.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;
import com.eval2.erpnext.model.PurchaseInvoice;
import com.eval2.erpnext.model.PurchaseInvoiceResponse;

import java.util.List;

@Service
public class FactureService {

    @Value("${erpnext.api.url}")
    private String erpnextApiUrl;

    private final RestTemplate restTemplate;

    public FactureService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PurchaseInvoice> getAllPurchaseInvoices(HttpSession session) {
        String sid = (String) session.getAttribute("sid");

        if (sid == null) {
            throw new RuntimeException("Aucune session active. Veuillez vous reconnecter.");
        }

        String url = erpnextApiUrl
                + "/api/resource/Purchase Invoice?fields=[\"*\"]&sid=" + sid;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, "sid=" + sid);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<PurchaseInvoiceResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, PurchaseInvoiceResponse.class);

            return response.getBody().getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des factures d'achat.");
        }
    }
}
