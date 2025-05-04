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

    // Constructeur
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    // Page d'index/login
    @GetMapping("/")
    public String index(Model model) {
        return "login";
    }

    // Page d'accueil après une connexion réussie
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        // Vérifier si l'utilisateur est connecté en vérifiant la session
        Object userSession = session.getAttribute("user");
        if (userSession != null) {
            model.addAttribute("user", userSession);
            return "template"; // Page d'accueil
        } else {
            return "redirect:/"; // Rediriger vers la page de connexion si pas de session active
        }
    }

    // Gérer la connexion de l'utilisateur
    @PostMapping("/login")
    public String login(
            @RequestParam String usr,
            @RequestParam String pwd,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        // Authentifier l'utilisateur via le service AuthService
        boolean loginOk = authService.authenticate(usr, pwd);

        if (loginOk) {
            // Créer une session pour l'utilisateur si la connexion est réussie
            session.setAttribute("user", usr); // Tu peux stocker plus d'informations si nécessaire
            return "redirect:/home"; // Rediriger vers la page d'accueil
        } else {
            // Si l'authentification échoue, ajouter un message d'erreur et rediriger
            redirectAttributes.addFlashAttribute("error", "Identifiants invalides.");
            return "redirect:/"; // Retourner à la page de connexion
        }
    }

    // Gérer la déconnexion de l'utilisateur
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Supprimer l'attribut 'user' de la session pour déconnecter l'utilisateur
        session.invalidate(); // Invalider la session entière
        return "redirect:/"; // Rediriger vers la page de connexion
    }

    

}
