package com.example.begin.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Console;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class MainController {
    @GetMapping("/bobr1")
    public String index(){
        return "home";
    }
    @GetMapping("/bobr2")
    public String about1(@RequestParam(name = "a", required = false, defaultValue = "бобры") String text, Model model){
        System.out.println(text);
        model.addAttribute("temp",text);
        return "about";
    }
    @PostMapping("/bobr2")
    public String about2(@RequestParam(name = "par") String par, Model model){
        model.addAttribute("temp",par);
        return "about";
    }
}
