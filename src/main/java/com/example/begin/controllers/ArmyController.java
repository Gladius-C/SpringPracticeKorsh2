package com.example.begin.controllers;

import com.example.begin.controllers.Repository.ArmyRepository;
import com.example.begin.controllers.Repository.CountryRepository;
import com.example.begin.controllers.Repository.RomanRepository;
import com.example.begin.controllers.models.Army;
import com.example.begin.controllers.models.Country;
import com.example.begin.controllers.models.Roman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArmyController {

    @Autowired
    ArmyRepository armyRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    RomanRepository romanRepository;

    public void pageInitialiser(Model model){
        Iterable<Army> armyIterable = armyRepository.findAll();
        Iterable<Roman> romanIterable = romanRepository.findAll();
        Iterable<Country> countryIterable = countryRepository.findAll();
        model.addAttribute("army_list",armyIterable);
        model.addAttribute("roman_list",romanIterable);
        model.addAttribute("country_list",countryIterable);
    }

    @GetMapping("/list3/")
    public String ListArmies(Model model, Army army, Roman roman){
        pageInitialiser(model);
        model.addAttribute("army", army);
        model.addAttribute("roman", roman);

        return ("army/army");
    }
    @GetMapping("/add3/")
    public  String AddArmyGet(Model model, Army army, Roman roman){
        pageInitialiser(model);
        return "redirect:/list3/";
    }

    @PostMapping("add3")
    public String AddArmyPost(Model model, Army army){
        armyRepository.save(army);
        return "redirect:/list3/";
    }
}
