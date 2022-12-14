package com.example.begin.controllers;

import com.example.begin.controllers.Repository.RomanRepository;
import com.example.begin.controllers.models.Battle;
import com.example.begin.controllers.models.Roman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.sql.Date;

@Controller
//@RequestMapping("/Romans")
public class RomanController {
    @Autowired
    RomanRepository romanRepository;

    @GetMapping("/list1ini")
    public String InitRoman(Model model,Roman roman)
    {
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
        model.addAttribute("roman", roman);
        //String typeInput = "hidden";
        //model.addAttribute("isHidden", typeInput);
        return "roman/roman";
    }

    @GetMapping("/list1")
    public String ListRoman(Model model, Roman roman){
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
        String typeInput = "hidden";
        model.addAttribute("roman", roman);
        model.addAttribute("isHidden", typeInput);
        return "roman/roman";
    }
    @GetMapping("/list1/filter")
    public String ListFilterRoman(@RequestParam(name = "name")String name, Model model, Roman roman) {
        Iterable<Roman> romanIterable = romanRepository.findByName(name);
        model.addAttribute("roman_list", romanIterable);
        String typeInput = "hidden";
        model.addAttribute("roman", roman);
        model.addAttribute("isHidden", typeInput);
        return "roman/roman";
    }
    @GetMapping("/list1/filterContains")
    public String ListFilterContainsRoman(@RequestParam(name = "name")String name,Model model, Roman roman) {
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
//            ,
//            @RequestParam(name = "name") String name,
//            @RequestParam(name = "dateOfBirth")Date date_of_birth,
//            @RequestParam(name = "majorDeeds")String major_deeds,
//            @RequestParam(name = "netWorth")Integer net_worth,
//            @RequestParam(name = "ethnicity")String ethnicity
            )
    {

        model.addAttribute("roman", roman);

        return ("redirect:/list1/");


    }

    @PostMapping("/add1")
    public String AddRomanPost(@Valid Roman roman, BindingResult bindingResult, Model model)

    {
            if (bindingResult.hasErrors()){
                String typeInput = "hidden";
                model.addAttribute("isHidden", typeInput);
                return "roman/roman";
            }
            //Roman new_roman = new Roman(name,date_of_birth,net_worth,major_deeds,ethnicity);
            romanRepository.save(roman);
            return "redirect:/list1/";


    }

    @GetMapping("/del1/{id}")
    public String DelRomanGet(@PathVariable Long id){
        Roman roman = romanRepository.findById(id).orElseThrow();
        romanRepository.delete(roman);
        return "redirect:/list1/";
    }

    @GetMapping("/details1/{id}")
    public String DetRomanGet(@PathVariable Long id, Model model){
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
    public  String UpdRomanPost(@ModelAttribute("roman") @Valid Roman roman, BindingResult bindingResult

    )
    {
        if (bindingResult.hasErrors()){
            return "roman/roman";
        }

        romanRepository.save(roman);

        return "redirect:/list1/";
    }


    @PostMapping("/updDet1")
    public  String UpdDetRomanPost(@ModelAttribute("roman") @Valid Roman roman, BindingResult bindingResult,
//                                @RequestParam Long id_roman,
//                                @RequestParam(name = "name") String name,
//                                @RequestParam(name = "dateOfBirth")Date date_of_birth,
//                                @RequestParam(name = "majorDeeds")String major_deeds,
//                                @RequestParam(name = "netWorth")Integer net_worth,
//                                @RequestParam(name = "ethnicity")String ethnicity
                                   Model model
    )
    {
        if (bindingResult.hasErrors()){
            return "roman/roman-detail";
        }
//        Roman edited_roman = romanRepository.findById(id_roman).orElseThrow();
//        edited_roman.setName(name);
//        edited_roman.setDateOfBirth(date_of_birth);
//        edited_roman.setMajorDeeds(major_deeds);
//        edited_roman.setNetWorth(net_worth);
//        edited_roman.setEthnicity(ethnicity);
        romanRepository.save(roman);

        return "redirect:/list1/";
    }
}
