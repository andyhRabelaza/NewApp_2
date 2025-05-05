package com.eval2.erpnext.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class CalendarController {

    @GetMapping("/calendrier")
    public String afficherCalendrier(Model model) {
        return "fragments/calendrier"; // Renvoyer la vue complète (sans utiliser th:fragment)
    }
}
