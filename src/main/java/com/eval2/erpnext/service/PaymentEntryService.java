package com.eval2.erpnext.service;

import com.eval2.erpnext.model.PaymentEntry;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentEntryService {

    @Value("${erpnext.api.url}")
    private String erpnextApiUrl;

    private final RestTemplate restTemplate;

    public PaymentEntryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String createPaymentEntry(HttpSession session, PaymentEntry paymentEntry) {
        String sid = (String) session.getAttribute("sid");

        if (sid == null) {
            throw new RuntimeException("Aucune session active. Veuillez vous reconnecter.");
        }

        String url = erpnextApiUrl + "/api/resource/Payment Entry";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.COOKIE, "sid=" + sid);

        HttpEntity<PaymentEntry> entity = new HttpEntity<>(paymentEntry, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, String.class);

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la création du Payment Entry.");
        }
    }
}
