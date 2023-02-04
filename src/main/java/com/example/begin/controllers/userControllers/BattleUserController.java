package com.example.begin.controllers.userControllers;

import com.example.begin.controllers.Repository.BattleRepository;
import com.example.begin.controllers.Repository.RomanRepository;
import com.example.begin.controllers.models.Battle;
import com.example.begin.controllers.models.Roman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BattleUserController {

    @Autowired
    BattleRepository battleRepository;
    @Autowired
    RomanRepository romanRepository;

    @GetMapping("/battleLibrary")
    public String ListBattle(Model model,Battle battle){
        Iterable<Battle> battleIterable = battleRepository.findAll();
        model.addAttribute("battle_list",battleIterable);
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
        String typeInput = "hidden";
        model.addAttribute("battle", battle);
        model.addAttribute("isHidden", typeInput);
        return "/userPages/userBattle";
    }
}
