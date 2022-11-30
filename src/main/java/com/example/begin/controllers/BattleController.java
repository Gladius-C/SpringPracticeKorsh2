package com.example.begin.controllers;


import com.example.begin.controllers.Repository.BattleRepository;
import com.example.begin.controllers.models.Battle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.sql.Date;

@Controller
public class BattleController {
    @Autowired
    BattleRepository BattleRepository;

    @GetMapping("list2/")
    public String ListBattle(Model model){
        Iterable<Battle> battleIterable = BattleRepository.findAll();
        model.addAttribute("battle_list",battleIterable);
        return "battle/battle";
    }

    @GetMapping("list2/add2")
    public String AddBattleGet(
            @RequestParam(name = "location") String location,
            @RequestParam(name = "date")Date date,
            @RequestParam(name = "defender")String defender,
            @RequestParam(name = "casualties")Integer casualties,
            @RequestParam(name = "attacker")String attacker,
            @RequestParam(name = "winner") String winner)
    {

        return ("redirect:/list2/");


    }

    @PostMapping("/list2/add2")
    public String AddBattlePost(
            @RequestParam(name = "location") String location,
            @RequestParam(name = "date")Date date,
            @RequestParam(name = "defender")String defender,
            @RequestParam(name = "casualties")Integer casualties,
            @RequestParam(name = "attacker")String attacker,
            @RequestParam(name = "winner") String winner)
    {

        Battle new_battle = new Battle(location,date,defender,attacker,casualties,winner);
        BattleRepository.save(new_battle);
        return ("redirect:/list2/");


    }

}