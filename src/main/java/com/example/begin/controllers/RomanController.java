package com.example.begin.controllers;

import com.example.begin.controllers.Repository.RomanRepository;
import com.example.begin.controllers.models.Roman;
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
//@RequestMapping("/Romans")
public class RomanController {
    @Autowired
    RomanRepository romanRepository;

    @GetMapping("/list1")
    public String ListRoman(Model model){
        Iterable<Roman> romanIterable = romanRepository.findAll();
        model.addAttribute("roman_list",romanIterable);
        return "/roman/roman";
    }

    @GetMapping("/add1")
    public String AddRomanGet(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "dateOfBirth")Date date_of_birth,
            @RequestParam(name = "majorDeeds")String major_deeds,
            @RequestParam(name = "netWorth")Integer net_worth,
            @RequestParam(name = "ethnicity")String ethnicity)
    {

        return ("redirect:/list1/");


    }

    @PostMapping("/add1")
    public String AddRomanPost(
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "dateOfBirth")Date date_of_birth,
                           @RequestParam(name = "majorDeeds")String major_deeds,
                           @RequestParam(name = "netWorth")Integer net_worth,
                           @RequestParam(name = "ethnicity")String ethnicity)
    {

            Roman new_roman = new Roman(name,date_of_birth,net_worth,major_deeds,ethnicity);
            romanRepository.save(new_roman);
            return ("redirect:/list1/");


    }

}
