package com.eval2.erpnext.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("title", "ErpNext de Connexion");
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {
        if ("andyhgael@gmail.com".equals(username) && "Andyh".equals(password)) {
            model.addAttribute("username", "Andy");
            return "home";
        } else {
            model.addAttribute("title", "Page de Connexion");
            model.addAttribute("error", "Identifiants incorrects");
            return "login";
        }
    }
}
