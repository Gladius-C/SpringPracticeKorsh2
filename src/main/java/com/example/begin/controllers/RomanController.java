package com.example.begin.controllers;

import com.example.begin.controllers.Repository.ArmyRepository;
import com.example.begin.controllers.Repository.BattleRepository;
import com.example.begin.controllers.Repository.RomanRepository;
import com.example.begin.controllers.models.Army;
import com.example.begin.controllers.models.Battle;
import com.example.begin.controllers.models.Roman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/Romans")
@PreAuthorize("hasAnyAuthority('ADMIN', 'HISTORIAN')")
public class RomanController {
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
    @GetMapping("/list1ini")
    public String InitRoman(Model model, Roman roman, Army army)
    {
        pageInitialiser(roman, model);
        String typeInput = "visible";
        model.addAttribute("isHidden", typeInput);
        return "roman/roman";
    }

    @GetMapping("/list1")
    public String ListRoman(Model model, Roman roman){
        pageInitialiser(roman, model);
        return "roman/roman";
    }
    @GetMapping("/list1/filter")
    public String ListFilterRoman(@RequestParam(name = "name")String name, Model model, Roman roman) {
        pageInitialiser(roman, model);
        Iterable<Roman> romanIterable = romanRepository.findByName(name);
        model.addAttribute("roman_list", romanIterable);
        String typeInput = "hidden";
        model.addAttribute("roman", roman);
        model.addAttribute("isHidden", typeInput);
        return "roman/roman";
    }
    @GetMapping("/list1/filterContains")
    public String ListFilterContainsRoman(@RequestParam(name = "name")String name,Model model, Roman roman) {
        pageInitialiser(roman, model);
        Iterable<Roman> romanIterable = romanRepository.findByNameContains(name);
        model.addAttribute("roman_list", romanIterable);
        String typeInput = "hidden";
        model.addAttribute("roman", roman);
        model.addAttribute("isHidden", typeInput);
        return "roman/roman";
    }

    @GetMapping("/add1")
    public String AddRomanGet(
            Roman roman,
            Model model
            )
    {
        pageInitialiser(roman, model);
        return ("redirect:/list1/");
    }

    @PostMapping("/add1")
    public String AddRomanPost( @Valid Roman roman, BindingResult bindingResult, Model model)
    {
            List<Roman> romanList = romanRepository.findAll();
            if (bindingResult.hasErrors()){
                pageInitialiser(roman, model);
                return "roman/roman";
            }
        for (Roman roman1:romanList
             ) { if ((roman1.getArmy()==roman.getArmy()) & ((roman1.getArmy() !=null) | (roman.getArmy()!=null))){
            pageInitialiser(roman, model);
            return "roman/roman";
            }
        }
            romanRepository.save(roman);
            return "redirect:/list1/";
    }

    @GetMapping("/del1/{id}")
    public String DelRomanGet(@PathVariable Long id){
        Roman roman = romanRepository.findById(id).orElseThrow();
        roman.setArmy(null);
        romanRepository.delete(roman);
        return "redirect:/list1/";
    }

    @GetMapping("/details1/{id}")
    public String DetRomanGet(@PathVariable Long id, Model model,Roman roman){
        pageInitialiser(roman, model);
        model.addAttribute("roman",romanRepository.findById(id).orElseThrow());
        return "roman/roman-detail";
    }

    @GetMapping("/permitGain1")
    public String PermitGainRomanGet(Model model){
        String typeInput = "visible";
        model.addAttribute("isHidden", typeInput);
        return "redirect:/list1ini/";
    }

    @GetMapping("/permitDeny1")
    public String PermitDenyRomanGet(){
        return "redirect:/list1/";
    }
    @PostMapping("/upd1")
    public  String UpdRomanPost(@ModelAttribute("roman") @Valid Roman roman, BindingResult bindingResult, Model model,
    @RequestParam(name = "UID")Long id)
    {

        List<Roman> romanList = romanRepository.findAll();
        Roman romanThis = romanRepository.findById(id).orElseThrow();
        if (bindingResult.hasErrors()){
            pageInitialiser(roman, model);
            String typeInput = "visible";
            model.addAttribute("isHidden", typeInput);
            return "roman/roman";
        }
        for (Roman roman1:romanList
        ) { if ((roman1.getArmy()==roman.getArmy()) & ((roman1.getArmy() !=null) | (roman.getArmy()!=null))&(roman1.getArmy()!=romanThis.getArmy())){
            pageInitialiser(roman, model);
            String typeInput = "visible";
            model.addAttribute("isHidden", typeInput);
            return "roman/roman";
        }
        }
        romanRepository.save(roman);

        return "redirect:/list1/";
    }


    @PostMapping("/updDet1")
    public  String UpdDetRomanPost(@ModelAttribute("roman") @Valid Roman roman, BindingResult bindingResult, Model model,
                     @RequestParam(name = "UID")Long id
    )
    {

        List<Roman> romanList = romanRepository.findAll();
        Roman romanThis = romanRepository.findById(id).orElseThrow();
        if (bindingResult.hasErrors()){
            pageInitialiser(roman, model);
            String typeInput = "visible";
            model.addAttribute("isHidden", typeInput);
            return "roman/roman-detail";
        }
        for (Roman roman1:romanList
        ) { if ((roman1.getArmy()==roman.getArmy()) & ((roman1.getArmy() !=null) | (roman.getArmy()!=null))&(roman1.getArmy()!=romanThis.getArmy())){
            pageInitialiser(roman, model);
            String typeInput = "visible";
            model.addAttribute("isHidden", typeInput);
            return "roman/roman-detail";
        }
        }
        romanRepository.save(roman);

        return "redirect:/list1/";
    }
}
