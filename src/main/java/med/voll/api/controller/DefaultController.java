package med.voll.api.controller;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@Controller
public class DefaultController {

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public String defaultMapping(Model model) {

        model.addAttribute("username", "Usuário");

        return "index";
    }
}
