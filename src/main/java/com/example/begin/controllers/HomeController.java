package com.example.begin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.PublicKey;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String initHome(){
        return "/home";
    }

}
