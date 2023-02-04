package com.example.begin.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.PublicKey;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String initHome(Model model){
        String isHidden = "hidden";
        isHidden = Reveal();
        model.addAttribute("isHidden",isHidden);

        return "/home";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'HISTORIAN')")
    private String Reveal(){
        return ("visible");
    }


}
