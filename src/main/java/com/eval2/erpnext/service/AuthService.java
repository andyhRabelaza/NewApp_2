package com.eval2.erpnext.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    @Value("${erpnext.api.url}")
    private String erpnextApiUrl;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String authenticate(String usr, String pwd) {
        String url = erpnextApiUrl + "/api/method/login";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("usr", usr);
        body.add("pwd", pwd);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                List<String> cookies = response.getHeaders().get("Set-Cookie");
                if (cookies != null) {
                    for (String cookie : cookies) {
                        if (cookie.startsWith("sid=")) {
                            return cookie.split(";")[0].split("=")[1]; // extraire le sid
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; 
    }
}
