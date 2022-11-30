package com.example.begin.controllers;

import com.example.begin.controllers.Repository.StarRepository;
import com.example.begin.controllers.models.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/star")
public class StarController {

        @Autowired
        StarRepository starRepository;

        @GetMapping("/star1")
        public String index(Model model){
            Iterable<Star> starIterable = starRepository.findAll();
            model.addAttribute("star_list", starIterable);
            return "star/index";
        }

        @GetMapping("star/add")
        public String AddView(){

            return "star/star-add";
        }

    /* @PostMapping("star/add")
    public String AddStar(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "classStar") String class_star,
            @RequestParam(name = "massStar") int mass_star
    ){
        Star new_star = new Star(name,class_star,mass_star);
        starRepository.save(new_star);
        return "redirect:/star1/";
    }*/

    @GetMapping("star/filter")
    public String FilterStar(@RequestParam(name = "name") String name_star, Model model){
        List<Star> starList = starRepository.findByName(name_star);
        model.addAttribute("star_list", starList);
        return "star/index";
    }

    @GetMapping("star/filtercontains")
    public String FilterContainsStar(@RequestParam(name = "name") String name_star, Model model){
        List<Star> starList = starRepository.findByNameContains(name_star);
        model.addAttribute("star_list", starList);
        return "star/index";
    }

    @GetMapping("star/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model
    )
    {
        Star star_obj = starRepository.findById(id).orElseThrow();
        model.addAttribute("one_star",star_obj);
        return "star/info";
    }

    @GetMapping("star/detail/{id}/del")
    public String deleteStar(@PathVariable Long id)
    {
        Star star= starRepository.findById(id).orElseThrow();
        starRepository.delete(star);
        return "redirect:/star1/";
    }

    @GetMapping("star/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, Model model)
    {
        model.addAttribute("object", starRepository.findById(id).orElseThrow());
        Star star= starRepository.findById(id).orElseThrow();
        starRepository.delete(star);
        return "redirect:/star1/";
    }
    @PostMapping("/detail/{id}/upd")
    public String update(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String classStar,
            @RequestParam Integer massStar
    ){
            Star star = starRepository.findById(id).orElseThrow();
            star.setName(name);
            star.setClassStar(classStar);
            star.setMassStar(massStar);
            starRepository.save(star);
            return "rediret:/star/detail/"+star.getUID();
    }

    @GetMapping("/add")
    public String AddView(Star star){
            return "star/star-add";
    }

    @PostMapping("/add")
    public String AddStar(@Valid Star star, BindingResult bindingResult,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "classStar") String class_star,
                          @RequestParam(name = "massStar") int mass_star)
    {
        if (bindingResult.hasErrors())
        {
            return "star/star-add";
        }
        else {
            Star new_star = new Star(name,class_star,mass_star);
            starRepository.save(new_star);
            return "redirect:/star1/";
        }
    }
}
