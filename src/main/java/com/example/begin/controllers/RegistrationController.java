package com.example.begin.controllers.Repository;
import com.example.begin.controllers.Repository.UserRepository;
import com.example.begin.controllers.models.Role;
import com.example.begin.controllers.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    private String regView(User user) {
        return "regis";
    }

    @GetMapping("/login")
    private  String login(){

        return "login";
    }

    @PostMapping("/registration")
    private String reg(@Valid User user, BindingResult bindingResult, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "/regis";
        }
        if(user.getUsername().equals("Nigger"))
            bindingResult.addError(new FieldError("user", "name",
                    "Недопустимое выражение!"));

        if(bindingResult.hasErrors())
            return"/regis";

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}