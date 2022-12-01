package com.example.begin.controllers;


import com.example.begin.controllers.Repository.BattleRepository;
import com.example.begin.controllers.models.Battle;
import com.example.begin.controllers.models.Battle;
import com.example.begin.controllers.models.Roman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.sql.Date;

@Controller
public class BattleController {
    @Autowired
    BattleRepository battleRepository;

    @GetMapping("/list2ini")
    public String InitBattle(Model model)
    {
        Iterable<Battle> battleIterable = battleRepository.findAll();
        model.addAttribute("battle_list",battleIterable);
        //String typeInput = "hidden";
        //model.addAttribute("isHidden", typeInput);
        return "battle/battleBlank";
    }
    @GetMapping("list2/")
    public String ListBattle(Model model){
        Iterable<Battle> battleIterable = battleRepository.findAll();
        model.addAttribute("battle_list",battleIterable);
        String typeInput = "hidden";
        model.addAttribute("isHidden", typeInput);
        return "battle/battleBlank";
    }

    @GetMapping("/list2/filter")
    public String ListFilterBattle(@RequestParam(name = "defender")String defender,
                                   //@RequestParam(name = "attacker")String attacker,
                                   Model model) {
        Iterable<Battle> battleIterable = battleRepository.findByDefenderOrAttacker(defender, defender);
        model.addAttribute("battle_list", battleIterable);
        String typeInput = "hidden";
        model.addAttribute("isHidden", typeInput);
        return "battle/battleBlank";
    }
    @GetMapping("/list2/filterContains")
    public String ListFilterContainsBattle(@RequestParam(name = "defender")String defender,
                                           //@RequestParam(name = "attacker")String attacker,
                                           Model model) {
        Iterable<Battle> battleIterable = battleRepository.findByDefenderOrAttackerContains(defender, defender);
        model.addAttribute("battle_list", battleIterable);
        String typeInput = "hidden";
        model.addAttribute("isHidden", typeInput);
        return "battle/battleBlank";
    }
    @GetMapping("list2/add2")
    public String AddBattleGet(
            @RequestParam(name = "location") String location,
            @RequestParam(name = "date")Date date,
            @RequestParam(name = "defender")String defender,
            @RequestParam(name = "attacker")String attacker,
            @RequestParam(name = "casualties")Integer casualties,
            @RequestParam(name = "winner") String winner)
    {

        return ("redirect:/list2/");


    }

    @PostMapping("/add2")
    public String AddBattlePost(
            @RequestParam(name = "location") String location,
            @RequestParam(name = "date")Date date,
            @RequestParam(name = "defender")String defender,
            @RequestParam(name = "attacker")String attacker,
            @RequestParam(name = "casualties")Integer casualties,
            @RequestParam(name = "winner") String winner)
    {

        Battle new_battle = new Battle(location,date,defender,attacker,casualties,winner);
        battleRepository.save(new_battle);
        return ("redirect:/list2/");


    }
    @GetMapping("/del2/{id}")
    public String DelBattleGet(@PathVariable Long id){
        Battle battle = battleRepository.findById(id).orElseThrow();
        battleRepository.delete(battle);
        return "redirect:/list2/";
    }

    @GetMapping("/details2/{id}")
    public String DetBattleGet(@PathVariable Long id, Model model){
        model.addAttribute("battle",battleRepository.findById(id).orElseThrow());
        return "battle/battle-detail";
    }

    @GetMapping("/permitGain2")
    public String PermitGainBattleGet(Model model){
        String typeInput = "visible";
        model.addAttribute("isHidden", typeInput);
        return "redirect:/list2ini/";
    }

    @GetMapping("/permitDeny2")
    public String PermitDenyBattleGet(){
        return "redirect:/list2/";
    }
    @PostMapping("/upd2")
    public  String UpdBattlePost(Model model,
                                @RequestParam Long id_battle,
                                 @RequestParam(name = "location") String location,
                                 @RequestParam(name = "date")Date date,
                                 @RequestParam(name = "defender")String defender,
                                 @RequestParam(name = "attacker")String attacker,
                                 @RequestParam(name = "casualties")Integer casualties,
                                 @RequestParam(name = "winner") String winner)
    {
        Battle edited_battle = battleRepository.findById(id_battle).orElseThrow();
        edited_battle.setLocation(location);
        edited_battle.setDate(date);
        edited_battle.setDefender(defender);
        edited_battle.setCasualties(casualties);
        edited_battle.setAttacker(attacker);
        edited_battle.setWinner(winner);
        battleRepository.save(edited_battle);

        return "redirect:/list2/";
    }


    @PostMapping("/updDet2")
    public  String UpdDetBattlePost(Model model,
                                   @RequestParam Long id_battle,
                                    @RequestParam(name = "location") String location,
                                    @RequestParam(name = "date")Date date,
                                    @RequestParam(name = "defender")String defender,
                                    @RequestParam(name = "casualties")Integer casualties,
                                    @RequestParam(name = "attacker")String attacker,
                                    @RequestParam(name = "winner") String winner)
    {
        Battle edited_battle = battleRepository.findById(id_battle).orElseThrow();
        edited_battle.setLocation(location);
        edited_battle.setDate(date);
        edited_battle.setDefender(defender);
        edited_battle.setCasualties(casualties);
        edited_battle.setAttacker(attacker);
        edited_battle.setWinner(winner);
        battleRepository.save(edited_battle);

        return "redirect:/list2/";
    }
}