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
        String sid = (String) session.getAttribute("sid");

        if (sid == null) {
            throw new RuntimeException("Aucune session active. Veuillez vous reconnecter.");
        }

        String url = erpnextApiUrl + "/api/resource/Supplier Quotation?fields=[\"*\"]&filters=[[\"supplier\",\"=\",\""
                + parametre1 + "\"]]&sid=" + sid;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, "sid=" + sid);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<SupplierQuotationResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, SupplierQuotationResponse.class);

            return response.getBody().getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des quotations de fournisseur.");
        }
    }

}
