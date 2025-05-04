package com.eval2.erpnext.controller;

import com.eval2.erpnext.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Object userSession = session.getAttribute("user");
        if (userSession != null) {
            model.addAttribute("user", userSession);
            return "template";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String usr,
            @RequestParam String pwd,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        String sid = authService.authenticate(usr, pwd);

        if (sid != null) {
            session.setAttribute("user", usr);
            session.setAttribute("password", pwd);
            session.setAttribute("sid", sid); // Stocker le sid pour les appels futurs
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("error", "Identifiants invalides.");
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
