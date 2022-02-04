package se.maxk.awsssosaml.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal Saml2AuthenticatedPrincipal principal) {
        var mail = principal.getFirstAttribute("emailAddress");
        model.addAttribute("email", mail);
        return "index";
    }
}
