package com.example.begin.controllers.userControllers;

import com.example.begin.controllers.Repository.ArmyRepository;
import com.example.begin.controllers.Repository.BattleRepository;
import com.example.begin.controllers.Repository.RomanRepository;
import com.example.begin.controllers.models.Army;
import com.example.begin.controllers.models.Battle;
import com.example.begin.controllers.models.Roman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RomanUserController {
    @Autowired
    RomanRepository romanRepository;

    @Autowired
    BattleRepository battleRepository;
    @Autowired
    ArmyRepository armyRepository;

    public void pageInitialiser(Roman roman, Model model)
    {
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
        Iterable<Army> armyIterable = armyRepository.findAll();
        model.addAttribute("army_list",armyIterable);
        Iterable<Battle> battleIterable = battleRepository.findAll();
        model.addAttribute("battle_list",battleIterable);
        String typeInput = "hidden";
        model.addAttribute("roman", roman);
        model.addAttribute("isHidden", typeInput);
    }


    @GetMapping("/romanLibrary")
    public String ListRoman(Model model, Roman roman){
        pageInitialiser(roman, model);
        return "/userPages/userRomans";
    }
}
