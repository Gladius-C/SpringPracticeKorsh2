package com.example.begin.controllers;


import com.example.begin.controllers.Repository.BattleRepository;
import com.example.begin.controllers.Repository.RomanRepository;
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
    @Autowired
    RomanRepository romanRepository;

    @GetMapping("/list2ini")
    public String InitBattle(Model model, Battle battle)
    {
        Iterable<Battle> battleIterable = battleRepository.findAll();
        model.addAttribute("battle_list",battleIterable);
        model.addAttribute("battle", battle);
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
        //String typeInput = "hidden";
        //model.addAttribute("isHidden", typeInput);
        return "battle/battleBlank";
    }
    @GetMapping("list2/")
    public String ListBattle(Model model,Battle battle){
        Iterable<Battle> battleIterable = battleRepository.findAll();
        model.addAttribute("battle_list",battleIterable);
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
        String typeInput = "hidden";
        model.addAttribute("battle", battle);
        model.addAttribute("isHidden", typeInput);
        return "battle/battleBlank";
    }

    @GetMapping("/list2/filter")
    public String ListFilterBattle(@RequestParam(name = "defender")String defender,
                                   //@RequestParam(name = "attacker")String attacker,
                                   Model model, Battle battle) {
        Iterable<Battle> battleIterable = battleRepository.findByDefenderOrAttacker(defender, defender);
        model.addAttribute("battle_list", battleIterable);
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
        String typeInput = "hidden";
        model.addAttribute("isHidden", typeInput);
        model.addAttribute("battle", battle);
        return "battle/battleBlank";
    }
    @GetMapping("/list2/filterContains")
    public String ListFilterContainsBattle(@RequestParam(name = "defender")String defender,
                                           //@RequestParam(name = "attacker")String attacker,
                                           Model model, Battle battle) {
        Iterable<Battle> battleIterable = battleRepository.findByDefenderOrAttackerContains(defender, defender);
        model.addAttribute("battle_list", battleIterable);
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
        String typeInput = "hidden";
        model.addAttribute("isHidden", typeInput);
        model.addAttribute("battle", battle);
        return "battle/battleBlank";
    }
    @GetMapping("add2")
    public String AddBattleGet(
            Battle battle, Model model)

    {

        model.addAttribute("battle", battle);
        return ("redirect:/list2/");


    }

    @PostMapping("/add2")
    public String AddBattlePost( @Valid Battle battle, BindingResult bindingResult, Model model)

    {
        if(bindingResult.hasErrors()){
            String typeInput = "hidden";
            model.addAttribute("isHidden", typeInput);
            Iterable<Battle> battleIterable = battleRepository.findAll();
            model.addAttribute("battle_list",battleIterable);
            model.addAttribute("battle", battle);
            Iterable<Roman> romanIterable = romanRepository.findAll();
            model.addAttribute("roman_list",romanIterable);
            return "battle/battleBlank";
        }

        battleRepository.save(battle);
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
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
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
    public  String UpdBattlePost(@ModelAttribute("battle") @Valid Battle battle, BindingResult bindingResult, Model model)

    {
        if (bindingResult.hasErrors()){
            String typeInput = "visible";
            model.addAttribute("isHidden", typeInput);
            Iterable<Battle> battleIterable = battleRepository.findAll();
            model.addAttribute("battle_list",battleIterable);
            model.addAttribute("battle", battle);
            Iterable<Roman> romanIterable = romanRepository.findAll();
            model.addAttribute("roman_list",romanIterable);
            return "battle/battleBlank";
        }
        battleRepository.save(battle);

        return "redirect:/list2/";
    }


    @PostMapping("/updDet2")
    public  String UpdDetBattlePost(@ModelAttribute("battle") @Valid Battle battle, BindingResult bindingResult, Model model)

    {
        if (bindingResult.hasErrors()){
            String typeInput = "visible";
            model.addAttribute("isHidden", typeInput);
            Iterable<Roman> romanIterable = romanRepository.findAll();
            model.addAttribute("roman_list",romanIterable);
            return "battle/battleBlank";
        }
        battleRepository.save(battle);

        return "redirect:/list2/";
    }
}