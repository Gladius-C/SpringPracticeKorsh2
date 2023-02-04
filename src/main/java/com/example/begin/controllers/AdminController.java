package com.example.begin.controllers;


import com.example.begin.controllers.Repository.UserRepository;
import com.example.begin.controllers.models.Roman;
import com.example.begin.controllers.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/adminPage")
    public String ViewUserList(Model model, User user) {
        Iterable<User> userIterable = userRepository.findAll();


        model.addAttribute("user_list", userIterable);
        model.addAttribute("isHidden", "hidden");

        return ("admin/admin");
    }

    @PostMapping("/adminAdd")
    public  String AddUserList(){

        return ("admin");
    }

    @GetMapping("/del/{id}")
    public String DelUserList(){

        return ("admin/admin");
        //return ("redirect:/ViewUserList");
    }

    @PostMapping("/adminUpd")
    public String UpdUserList(@ModelAttribute("user")  User user, Model model,
                              @RequestParam(name = "id")Long id){
        model.addAttribute("isHidden", "hidden");

        userRepository.save(user);
        //return ("admin/admin");
        return ("redirect:/adminPage");
    }

}
